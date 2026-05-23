// package com.college.platform.alumni_platform.controller;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;

// @Controller
// public class PaymentPageController {

//     @Value("${razorpay.key}")
//     private String razorpayKey;

//     @GetMapping("/pay")
//     public String paymentPage(
//             String orderId,
//             Integer amount,
//             Model model) {

//         model.addAttribute("orderId", orderId);
//         model.addAttribute("amount", amount);
//         model.addAttribute("key", razorpayKey);

//         return "payment";
//     }
// }








package com.college.platform.alumni_platform.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentPageController {

    @Value("${razorpay.key}")
    private String razorpayKey;

    /**
     * Serves the Razorpay payment page.
     *
     * Called by the frontend after selectApplicant() gets order details:
     *   window.location.href = `/pay?orderId=...&amount=...&jobTitle=...&studentEmail=...&token=...`
     *
     * All params come from the JSON returned by
     *   POST /api/v1/alumni/jobs/applications/{id}/select
     */
    @GetMapping("/pay")
    public String paymentPage(
            @RequestParam(required = false) String orderId,
            @RequestParam(required = false) Long   amount,
            @RequestParam(required = false) String currency,
            @RequestParam(required = false) String jobTitle,
            @RequestParam(required = false) String studentEmail,
            @RequestParam(required = false) String token,   // JWT — passed so payment.html can call /verify
            Model model) {

        model.addAttribute("orderId",      orderId      != null ? orderId      : "");
        model.addAttribute("amount",       amount       != null ? amount       : 0L);
        model.addAttribute("currency",     currency     != null ? currency     : "INR");
        model.addAttribute("jobTitle",     jobTitle     != null ? jobTitle     : "");
        model.addAttribute("studentEmail", studentEmail != null ? studentEmail : "");
        model.addAttribute("token",        token        != null ? token        : "");
        model.addAttribute("key",          razorpayKey);

        return "payment";
    }
}