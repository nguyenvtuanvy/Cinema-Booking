package com.example.cinema_booking.request;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = "Full Name should not be empty")
    private String fullName;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    @NotEmpty(message = "Password should not be empty")
    private String password;
    @NotEmpty(message = "Phone Number should not be empty")
    @Size(message = "Phone Number should not exceed 10 characters",max = 10)
    private String phoneNumber;
    @NotNull(message = "Birthday cannot be null")
    @Past(message = "Birthday must be in the past")
    private LocalDate birthday;
    @NotNull(message = "Gender should not be null")
    @Min(value = 0, message = "Gender should be a positive number or zero")
    private Integer gender;
}
