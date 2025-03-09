package com.example.cinema_booking.service.customer;

import com.example.cinema_booking.dto.CustomerDTO;
import com.example.cinema_booking.repository.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepo customerRepo;


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
}
