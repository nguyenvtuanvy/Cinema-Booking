package com.example.cinema_booking.controller.home;

import com.example.cinema_booking.exception.LoginException;
import com.example.cinema_booking.request.AuthenticationRequest;
import com.example.cinema_booking.request.RegisterRequest;
import com.example.cinema_booking.response.AuthenticationResponse;
import com.example.cinema_booking.service.authentication.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestLoginController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/login")
    public String TestLogin(Model model){
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        model.addAttribute("customer", authenticationRequest);
        return "common/login";
    }

    @PostMapping ("/home")
    public String TestLoginSubmit(@Valid @ModelAttribute("customer") AuthenticationRequest authenticationRequest,
                                  Model model, HttpServletResponse httpServletResponse) throws LoginException {
        AuthenticationResponse response = authenticationService.authenticate(authenticationRequest, httpServletResponse);

        String message = "Đăng nhập thành công";
        model.addAttribute("message", message);
        return "common/home";
    }
}
