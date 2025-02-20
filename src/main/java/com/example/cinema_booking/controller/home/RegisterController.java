package com.example.cinema_booking.controller.home;

import com.example.cinema_booking.exception.RegisterException;
import com.example.cinema_booking.request.RegisterRequest;
import com.example.cinema_booking.service.authentication.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class RegisterController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) {
        try{
            String message= authenticationService.register(request);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }catch (RegisterException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
