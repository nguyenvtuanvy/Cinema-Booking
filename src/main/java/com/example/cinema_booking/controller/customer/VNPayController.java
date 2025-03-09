package com.example.cinema_booking.controller.customer;

import com.example.cinema_booking.service.vnpay.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customer/vnpay")
public class VNPayController {
    @Autowired
    private VNPayService vnPayService;

    @PostMapping("/create-order")
    public ResponseEntity<Map<String, String>> createOrder(@RequestParam("amount") int orderTotal,
                                                           @RequestParam("orderInfo") String orderInfo,
                                                           @RequestParam("returnUrl") String returnUrl,
                                                           HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnPayUrl = vnPayService.createOrder(request, orderTotal, orderInfo, returnUrl);
        Map<String, String> response = new HashMap<>();
        response.put("paymentUrl", vnPayUrl);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/payment-return")
    public ResponseEntity<Map<String, Object>> paymentCompleted(HttpServletRequest request) {
        int paymentStatus = vnPayService.orderReturn(request);

        Map<String, Object> response = new HashMap<>();
        response.put("orderId", request.getParameter("vnp_TxnRef"));
        response.put("totalPrice", request.getParameter("vnp_Amount"));
        response.put("paymentTime", request.getParameter("vnp_PayDate"));
        response.put("transactionId", request.getParameter("vnp_TransactionNo"));
        response.put("paymentStatus", paymentStatus == 1 ? "Success" : "Failed");

        return ResponseEntity.ok(response);
    }
}