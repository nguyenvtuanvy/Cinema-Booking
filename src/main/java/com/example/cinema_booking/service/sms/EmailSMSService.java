package com.example.cinema_booking.service.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class EmailSMSService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendOTP(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Mã xác nhận OTP");
        message.setText("Mã OTP của bạn là: " + otp);

        emailSender.send(message);
    }
}
