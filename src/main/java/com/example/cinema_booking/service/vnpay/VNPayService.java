package com.example.cinema_booking.service.vnpay;

import jakarta.servlet.http.HttpServletRequest;

public interface VNPayService {
    String createOrder(HttpServletRequest request, int amount, String orderInfo, String urlReturn);

    int orderReturn(HttpServletRequest request);
}
