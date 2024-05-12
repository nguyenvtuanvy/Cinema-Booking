package com.example.cinema_booking.controller.home;

import com.example.cinema_booking.exception.LoginException;
import com.example.cinema_booking.exception.RegisterException;
import com.example.cinema_booking.request.AuthenticationRequest;
import com.example.cinema_booking.request.RegisterRequest;
import com.example.cinema_booking.service.authentication.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {
    private final AuthenticationService authenticationService;
    @GetMapping("/demo")
    public ResponseEntity<String> getText(){
        return ResponseEntity.ok("Hello Word!!!");
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest UserRegisterRequest){
        try{
            String message = authenticationService.register(UserRegisterRequest);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (RegisterException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest UserLoginRequest){
//        try{
//            return ResponseEntity.status(HttpStatus.OK).body(authenticationService.authenticate(UserLoginRequest));
//        } catch (LoginException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
}
