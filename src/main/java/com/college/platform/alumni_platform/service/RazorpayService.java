// package com.college.platform.alumni_platform.service;

// import com.razorpay.Order;
// import com.razorpay.RazorpayClient;
// import org.json.JSONObject;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// @Service
// public class RazorpayService {

//     @Value("${razorpay.key}")
//     private String key;

//     @Value("${razorpay.secret}")
//     private String secret;

//     public Order createOrder(Double amount) throws Exception {

//         RazorpayClient razorpay = new RazorpayClient(key, secret);

//         JSONObject options = new JSONObject();
//         options.put("amount", (int)(amount * 100)); // convert to paise
//         options.put("currency", "INR");
//         options.put("receipt", "txn_" + System.currentTimeMillis());

//         return razorpay.orders.create(options);
//     }
// }




// package com.college.platform.alumni_platform.service;

// import com.razorpay.Order;
// import com.razorpay.RazorpayClient;
// import org.json.JSONObject;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import javax.crypto.Mac;
// import javax.crypto.spec.SecretKeySpec;
// import java.util.Base64;

// @Service
// public class RazorpayService {

//     @Value("${razorpay.key}")
//     private String key;

//     @Value("${razorpay.secret}")
//     private String secret;

//     // ✅ CREATE ORDER
//     public Order createOrder(Double amount) throws Exception {

//         RazorpayClient razorpay = new RazorpayClient(key, secret);

//         JSONObject options = new JSONObject();
//         options.put("amount", (int) (amount * 100)); // convert to paise
//         options.put("currency", "INR");
//         options.put("receipt", "txn_" + System.currentTimeMillis());

//         return razorpay.orders.create(options);
//     }

// // ✅ GENERATE SIGNATURE (CORRECT HEX VERSION)
// public String generateSignature(String orderId, String paymentId) throws Exception {

//     String payload = orderId + "|" + paymentId;

//     Mac sha256Hmac = Mac.getInstance("HmacSHA256");
//     SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
//     sha256Hmac.init(secretKey);

//     byte[] hash = sha256Hmac.doFinal(payload.getBytes());

//     // Convert to HEX (IMPORTANT)
//     StringBuilder hexString = new StringBuilder();
//     for (byte b : hash) {
//         String hex = Integer.toHexString(0xff & b);
//         if (hex.length() == 1) {
//             hexString.append('0');
//         }
//         hexString.append(hex);
//     }

//     return hexString.toString();
// }
// }






package com.college.platform.alumni_platform.service;

import com.college.platform.alumni_platform.entity.Payment;
import com.college.platform.alumni_platform.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Optional;

@Service
public class RazorpayService {

    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    @Value("${razorpay.webhook.secret:}")
    private String webhookSecret;

    @Autowired
    private PaymentRepository paymentRepository;

    // ===============================
    // ✅ CREATE ORDER
    // ===============================
    public Order createOrder(Double amount) throws Exception {

        RazorpayClient razorpay = new RazorpayClient(key, secret);

        JSONObject options = new JSONObject();
        options.put("amount", (int) (amount * 100));
        options.put("currency", "INR");
        options.put("receipt", "txn_" + System.currentTimeMillis());

        Order order = razorpay.orders.create(options);

        // Save initial payment record
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setRazorpayOrderId(order.get("id"));
        payment.setStatus(Payment.PaymentStatus.CREATED);

        paymentRepository.save(payment);

        return order;
    }

    // ===============================
    // ✅ VERIFY FRONTEND SIGNATURE
    // ===============================
    public String generateSignature(String orderId, String paymentId) throws Exception {

        String payload = orderId + "|" + paymentId;

        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
        sha256Hmac.init(secretKey);

        byte[] hash = sha256Hmac.doFinal(payload.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }

    // ===============================
    // ✅ VERIFY WEBHOOK SIGNATURE (HEX)
    // ===============================
    public boolean verifyWebhookSignature(String payload, String actualSignature) throws Exception {

        Mac sha256Hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(webhookSecret.getBytes(), "HmacSHA256");
        sha256Hmac.init(secretKey);

        byte[] hash = sha256Hmac.doFinal(payload.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        String generatedSignature = hexString.toString();

        return generatedSignature.equals(actualSignature);
    }

    // ===============================
    // ✅ PROCESS WEBHOOK (IDEMPOTENT)
    // ===============================
    public void processWebhook(String payload) throws Exception {

        JSONObject json = new JSONObject(payload);
        String event = json.getString("event");

        if ("payment.captured".equals(event)) {

            JSONObject paymentEntity = json
                    .getJSONObject("payload")
                    .getJSONObject("payment")
                    .getJSONObject("entity");

            String paymentId = paymentEntity.getString("id");
            String orderId = paymentEntity.getString("order_id");

            Optional<Payment> existingPayment =
                    paymentRepository.findByRazorpayPaymentId(paymentId);

            // 🔥 IDEMPOTENCY PROTECTION
            if (existingPayment.isPresent() &&
                    existingPayment.get().getStatus() == Payment.PaymentStatus.SUCCESS) {
                return; // Already processed
            }

            // Find by orderId if exists
            Payment payment = paymentRepository
                    .findByRazorpayOrderId(orderId)
                    .orElse(new Payment());

            payment.setRazorpayPaymentId(paymentId);
            payment.setRazorpayOrderId(orderId);
            payment.setStatus(Payment.PaymentStatus.SUCCESS);

            paymentRepository.save(payment);
        }
    }
}