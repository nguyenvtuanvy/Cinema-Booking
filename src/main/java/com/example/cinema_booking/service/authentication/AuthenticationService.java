package com.example.cinema_booking.service.authentication;

import com.example.cinema_booking.exception.LoginException;
import com.example.cinema_booking.exception.RegisterException;
import com.example.cinema_booking.request.AuthenticationRequest;
import com.example.cinema_booking.request.RegisterRequest;
import com.example.cinema_booking.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    String register(RegisterRequest registerRequest) throws RegisterException;

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest, HttpServletResponse httpServletResponse) throws LoginException;
}
