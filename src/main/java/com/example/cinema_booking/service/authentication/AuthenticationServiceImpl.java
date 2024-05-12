package com.example.cinema_booking.service.authentication;

import com.example.cinema_booking.entity.Customer;
import com.example.cinema_booking.exception.LoginException;
import com.example.cinema_booking.exception.RegisterException;
import com.example.cinema_booking.repository.CustomerRepo;
import com.example.cinema_booking.request.AuthenticationRequest;
import com.example.cinema_booking.request.RegisterRequest;
import com.example.cinema_booking.response.AuthenticationResponse;
import com.example.cinema_booking.service.jwt.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
    private final CustomerRepo customerRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String register(RegisterRequest request) throws RegisterException {
        if (request.getEmail() != null){
            var customer = customerRepo.findCustomerByEmail(request.getEmail());
            if (customer.isPresent()){
                throw new RegisterException("Email đã được sử dụng để đăng ký");
            }
        }

        try {
            Customer customer = Customer.builder()
                    .fullName(request.getFullName())
                    .email(request.getEmail())
                    .phoneNumber(request.getPhoneNumber())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .gender(request.getGender())
                    .birthday(request.getBirthday())
                    .role("USER")
                    .isBlocked(false)
                    .build();

            customerRepo.save(customer);

            return "Đăng ký thành công";
        } catch (Exception e){
            throw new RegisterException(e.getMessage());
        }
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request, HttpServletResponse httpServletResponse) throws LoginException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            var customer = customerRepo.findCustomerByEmail(request.getEmail()).orElseThrow();

            var token = jwtService.generateToken(customer);

            Cookie cookie = new Cookie("token", token);
            cookie.setSecure(false);
            cookie.setHttpOnly(true);
            httpServletResponse.addCookie(cookie);

            return AuthenticationResponse.builder()
                    .token(token)
                    .build();
        } catch (Exception e){
            throw new LoginException(e.getMessage());
        }
    }
}
