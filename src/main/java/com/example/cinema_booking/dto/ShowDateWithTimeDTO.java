package com.example.cinema_booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateShow;
    private Set<ShowTimeDTO> showTimes;
}
