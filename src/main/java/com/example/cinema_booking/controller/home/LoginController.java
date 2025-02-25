package com.example.cinema_booking.controller.home;

import com.example.cinema_booking.exception.LoginException;
import com.example.cinema_booking.request.AuthenticationRequest;
import com.example.cinema_booking.response.AuthenticationResponse;
import com.example.cinema_booking.service.authentication.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class LoginController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request, HttpServletResponse response){
        try{
            AuthenticationResponse authenticationResponse= authenticationService.authenticate(request, response);
//            cookieUtil.generatorTokenCookie(response, authenticationResponse);
            return ResponseEntity.status(200).body(authenticationResponse);
        }catch (LoginException e){
            Map<String,String> error= new HashMap<>();
            error.put("error","Authenticated failed");
            error.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
