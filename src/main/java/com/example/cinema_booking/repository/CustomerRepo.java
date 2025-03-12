package com.example.cinema_booking.repository;

import com.example.cinema_booking.dto.CustomerDTO;
import com.example.cinema_booking.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByEmail(String email);

    @Query("select new com.example.cinema_booking.dto.CustomerDTO(c.id, c.fullName, c.email, c.birthday, c.gender, c.phoneNumber) from Customer c where c.id = :customerId")
    CustomerDTO findCustomerById(Long customerId);

    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.fullName = :fullName, c.birthday = :birthday, c.gender = :gender, c.phoneNumber = :phoneNumber WHERE c.id = :customerId")
    void updateCustomerProfile(
            @Param("customerId") Long customerId,
            @Param("fullName") String fullName,
            @Param("birthday") LocalDate birthday,
            @Param("gender") String gender,
            @Param("phoneNumber") String phoneNumber
    );


    @Modifying
    @Transactional
    @Query("UPDATE Customer c SET c.password = :newPassword WHERE c.email = :email")
    void updatePasswordByEmail(@Param("email") String email, @Param("newPassword") String newPassword);
}
