package com.example.cinema_booking.controller.customer;

import com.example.cinema_booking.dto.RoomWithGroupedChairsDTO;
import com.example.cinema_booking.exception.ChairException;
import com.example.cinema_booking.service.customer.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {
    private CustomerService customerService;

    @GetMapping("/profile/{customerId}")
    public ResponseEntity<?> getChairsByMovieAndShowDateAndShowTime(@PathVariable Long customerId) {
        try {
            return ResponseEntity.ok(customerService.getCustomerById(customerId));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi: " + ex.getMessage());
        }
    }

    @PutMapping("/profile/{customerId}")
    public ResponseEntity<?> updateCustomerProfile(
            @PathVariable Long customerId,
            @RequestParam String fullName,
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate birthday,
            @RequestParam String gender,
            @RequestParam String phoneNumber
    ) {
        try {
            String message = customerService.updateCustomerProfile(customerId, fullName, birthday, gender, phoneNumber);
            return ResponseEntity.ok(message);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi: " + ex.getMessage());
        }
    }

    @PostMapping("/send-otp-email")
    public ResponseEntity<String> sendOTPByEmail(@RequestParam String email) {
        try {
            String response = customerService.sendOTPByEmail(email);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi: " + ex.getMessage());
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOTP(@RequestParam String email, @RequestParam String otp) {
        String response = customerService.verifyOTP(email, otp);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @RequestParam String email,
            @RequestParam String newPassword
    ) {
        String response = customerService.changePassword(email, newPassword);
        return ResponseEntity.ok(response);
    }
}
