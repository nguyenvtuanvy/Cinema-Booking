package com.example.cinema_booking.config;

import com.example.cinema_booking.entity.Customer;
import com.example.cinema_booking.entity.Manager;
import com.example.cinema_booking.repository.CustomerRepo;
import com.example.cinema_booking.repository.ManagerRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
public class ApplicationConfig {
    private final CustomerRepo customerRepo;
    private final ManagerRepo managerRepo;
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            // Tìm kiếm Customer
            Optional<Customer> customer = customerRepo.findCustomerByEmail(email);
            if (customer.isPresent()) {
                Customer customerEntity = customer.get();
                return User.withUsername(customerEntity.getEmail())
                        .password(customerEntity.getPassword())
                        .authorities(customerEntity.getAuthorities()) // Đảm bảo Customer có phương thức getAuthorities()
                        .build();
            }

            // Tìm kiếm Manager
            Optional<Manager> manager = managerRepo.findManagerByEmail(email);
            if (manager.isPresent()) {
                Manager managerEntity = manager.get();
                return User.withUsername(managerEntity.getEmail())
                        .password(managerEntity.getPassword())
                        .authorities(managerEntity.getAuthorities()) // Đảm bảo Manager có phương thức getAuthorities()
                        .build();
            }

            throw new UsernameNotFoundException("Email not found: " + email);
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}