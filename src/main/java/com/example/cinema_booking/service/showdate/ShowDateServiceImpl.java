package com.example.cinema_booking.service.showdate;

import com.example.cinema_booking.dto.PromotionDTO;
import com.example.cinema_booking.dto.ShowDateDTO;
import com.example.cinema_booking.dto.ShowDateWithTimeDTO;
import com.example.cinema_booking.dto.ShowTimeDTO;
import com.example.cinema_booking.exception.PromotionException;
import com.example.cinema_booking.exception.ShowDateException;
import com.example.cinema_booking.exception.ShowTimeException;
import com.example.cinema_booking.repository.ShowDateRepo;
import com.example.cinema_booking.service.showtime.ShowTimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShowDateServiceImpl implements ShowDateService{
    private final ShowDateRepo showDateRepo;
    private final ShowTimeService showTimeService;
    @Override
    public Set<ShowDateWithTimeDTO> findAllShowDateByMovieId(Long movieId) throws ShowDateException {
        Set<ShowDateDTO> showDates = showDateRepo.findShowDateByMovieId(movieId);
        if (showDates.isEmpty()) {
            throw new ShowDateException("No showDates found");
        }

        return showDates.stream()
                .map(showDate -> {
                    try {
                        Set<ShowTimeDTO> showTimes = showTimeService.findShowTimesByShowDateId(showDate.getId());

                        return new ShowDateWithTimeDTO(showDate.getId(), showDate.getDateShow(), showTimes);
                    } catch (ShowTimeException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toSet());
    }
}
