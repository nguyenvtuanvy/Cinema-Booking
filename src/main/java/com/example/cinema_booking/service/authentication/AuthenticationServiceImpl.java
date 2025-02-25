package com.example.cinema_booking.service.authentication;

import com.example.cinema_booking.entity.Customer;
import com.example.cinema_booking.entity.Manager;
import com.example.cinema_booking.entity.Role;
import com.example.cinema_booking.exception.LoginException;
import com.example.cinema_booking.exception.RegisterException;
import com.example.cinema_booking.repository.CustomerRepo;
import com.example.cinema_booking.repository.ManagerRepo;
import com.example.cinema_booking.repository.RoleRepo;
import com.example.cinema_booking.request.AuthenticationRequest;
import com.example.cinema_booking.request.ManagerRegisterRequest;
import com.example.cinema_booking.request.RegisterRequest;
import com.example.cinema_booking.response.AuthenticationResponse;
import com.example.cinema_booking.service.jwt.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{
    private final CustomerRepo customerRepo;
    private final ManagerRepo managerRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public String registerUser(RegisterRequest request) throws RegisterException {
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
    @Transactional
    public String registerAdmin(ManagerRegisterRequest request) throws RegisterException {
        if (request.getEmail() != null){
            var manager = managerRepo.findManagerByEmail(request.getEmail());
            if (manager.isPresent()){
                throw new RegisterException("Email đã được sử dụng để đăng ký");
            }
        }

        try {
            Role role = roleRepo.findById(1L).orElseThrow();
            Manager manager = Manager.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();

            managerRepo.save(manager);

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

            var customer = customerRepo.findCustomerByEmail(request.getEmail());
            if (customer.isPresent()) {
                var token = jwtService.generateToken(customer.get());
                return AuthenticationResponse.builder()
                        .userId(customer.get().getId())
                        .email(customer.get().getEmail())
                        .token(token)
                        .role("USER")
                        .build();
            }

            var manager = managerRepo.findManagerByEmail(request.getEmail());
            if (manager.isPresent()) {
                var token = jwtService.generateToken(manager.get());
                return AuthenticationResponse.builder()
                        .userId(manager.get().getId())
                        .email(manager.get().getEmail())
                        .token(token)
                        .role("ADMIN")
                        .build();
            }

            throw new LoginException("Email hoặc mật khẩu không chính xác");
        } catch (Exception e) {
            throw new LoginException(e.getMessage());
        }
    }
}
