package com.example.cinema_booking.controller.home;

import com.example.cinema_booking.exception.RegisterException;
import com.example.cinema_booking.request.RegisterRequest;
import com.example.cinema_booking.service.authentication.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
public class TestRegisterController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/register")
    public String TestRegister(Model model){
        RegisterRequest registerRequest = new RegisterRequest();
        model.addAttribute("customer", registerRequest);
        return "common/register";
    }

    @PostMapping("/register/save")
    public String TestRegisterSave(@Valid @ModelAttribute("customer") RegisterRequest registerRequest,
                                   Model model) throws RegisterException {
        String message = authenticationService.register(registerRequest);
        model.addAttribute("message", message);
        return "common/home";
    }
}
