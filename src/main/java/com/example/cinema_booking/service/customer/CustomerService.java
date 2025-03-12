package com.example.cinema_booking.service.customer;

import com.example.cinema_booking.dto.CustomerDTO;

import java.time.LocalDate;

public interface CustomerService {
    CustomerDTO getCustomerById(Long customerId);

    String updateCustomerProfile(Long customerId, String fullName, LocalDate birthday, String gender, String phoneNumber) throws Exception;


    String sendOTPByEmail(String email); // Gửi OTP qua email

    String verifyOTP(String email, String otp); // Xác thực OTP

    String changePassword(String email, String newPassword); // Đổi mật khẩu
}
