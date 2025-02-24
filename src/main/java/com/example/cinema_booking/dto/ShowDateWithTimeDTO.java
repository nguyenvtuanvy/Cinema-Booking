package com.example.cinema_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowDateWithTimeDTO {
    private Long id;
    private Date dateShow;
    private Set<ShowTimeDTO> showTimes;
}
