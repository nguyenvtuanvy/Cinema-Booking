package com.example.cinema_booking.response;

import com.example.cinema_booking.dto.MovieDTO;
import com.example.cinema_booking.dto.ShowDateDTO;
import com.example.cinema_booking.dto.ShowTimeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformationMovieResponse {
    private MovieDTO movieDTO;
    private Set<ShowDateDTO> showDates;
    private Set<ShowTimeDTO> showTimes;
}
