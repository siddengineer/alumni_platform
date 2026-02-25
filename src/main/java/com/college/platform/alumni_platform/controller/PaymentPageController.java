package com.college.platform.alumni_platform.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentPageController {

    @Value("${razorpay.key}")
    private String razorpayKey;

    @GetMapping("/pay")
    public String paymentPage(
            String orderId,
            Integer amount,
            Model model) {

        model.addAttribute("orderId", orderId);
        model.addAttribute("amount", amount);
        model.addAttribute("key", razorpayKey);

        return "payment";
    }
}