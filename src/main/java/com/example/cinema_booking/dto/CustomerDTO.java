package com.example.cinema_booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private Long id;
    private String fullName;
    private String email;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthday;
    private Integer gender;
    private String phoneNumber;
}
