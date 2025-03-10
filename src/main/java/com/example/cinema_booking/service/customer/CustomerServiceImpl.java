package com.example.cinema_booking.service.customer;

import com.example.cinema_booking.dto.CustomerDTO;
import com.example.cinema_booking.entity.Customer;
import com.example.cinema_booking.repository.CustomerRepo;
import com.example.cinema_booking.service.sms.EmailSMSService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepo customerRepo;
    private final EmailSMSService emailSMSService;
    private final PasswordEncoder passwordEncoder;
    private final Map<String, String> otpStorage = new HashMap<>();

    @Override
    public CustomerDTO getCustomerById(Long customerId) {
        return customerRepo.findCustomerById(customerId);
    }

    @Override
    public String updateCustomerProfile(Long customerId, String fullName, LocalDate birthday, String gender, String phoneNumber) throws Exception {
        if (!customerRepo.existsById(customerId)) {
            throw new Exception("Customer not found with ID: " + customerId);
        }

        customerRepo.updateCustomerProfile(customerId, fullName, birthday, gender, phoneNumber);

        return "Cập nhật thành công";
    }

    @Override
    public String sendOTPByEmail(String email) {
        Customer customer = customerRepo.findCustomerByEmail(email)
                .orElseThrow(() -> new RuntimeException("Không tim thấy email trong hệ thống"));

        String otp = generateOTP();
        otpStorage.put(email, otp);

        emailSMSService.sendOTP(email, otp);

        return "Mã OTP đã được gửi đến email của bạn";
    }

    @Override
    public String verifyOTP(String email, String otp) {
        String storedOTP = otpStorage.get(email);
        if (storedOTP == null || !storedOTP.equals(otp)) {
            return "Mã OTP không hợp lệ";
        }

        otpStorage.remove(email);

        return "Xác thực OTP thành công";
    }

    @Override
    public String changePassword(String email, String newPassword) {
        Customer customer = customerRepo.findCustomerByEmail(email)
                .orElseThrow(() -> new RuntimeException("Không tim thấy số điện thoại trong hệ thống"));


        customerRepo.updatePasswordByEmail(email, passwordEncoder.encode(newPassword));

        return "Đổi mật khẩu thành công";
    }

    private String generateOTP() {
        return String.format("%06d", new Random().nextInt(999999));
    }
}
