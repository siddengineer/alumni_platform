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




package com.college.platform.alumni_platform.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class RazorpayService {

    @Value("${razorpay.key}")
    private String key;

    @Value("${razorpay.secret}")
    private String secret;

    // ✅ CREATE ORDER
    public Order createOrder(Double amount) throws Exception {

        RazorpayClient razorpay = new RazorpayClient(key, secret);

        JSONObject options = new JSONObject();
        options.put("amount", (int) (amount * 100)); // convert to paise
        options.put("currency", "INR");
        options.put("receipt", "txn_" + System.currentTimeMillis());

        return razorpay.orders.create(options);
    }

// ✅ GENERATE SIGNATURE (CORRECT HEX VERSION)
public String generateSignature(String orderId, String paymentId) throws Exception {

    String payload = orderId + "|" + paymentId;

    Mac sha256Hmac = Mac.getInstance("HmacSHA256");
    SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
    sha256Hmac.init(secretKey);

    byte[] hash = sha256Hmac.doFinal(payload.getBytes());

    // Convert to HEX (IMPORTANT)
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
}