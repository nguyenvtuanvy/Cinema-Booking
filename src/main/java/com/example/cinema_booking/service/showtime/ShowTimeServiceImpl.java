package com.example.cinema_booking.service.showtime;

import com.example.cinema_booking.dto.ShowDateDTO;
import com.example.cinema_booking.dto.ShowTimeDTO;
import com.example.cinema_booking.exception.ShowDateException;
import com.example.cinema_booking.exception.ShowTimeException;
import com.example.cinema_booking.repository.ShowDateRepo;
import com.example.cinema_booking.repository.ShowTimeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService{
    private final ShowTimeRepo showTimeRepo;
    @Override
    public Set<ShowTimeDTO> findShowTimesByShowDateId(Long showDateId) throws ShowTimeException {
        Set<ShowTimeDTO> showTimes = showTimeRepo.findShowTimesByShowDateId(showDateId);
        if (showTimes.isEmpty()) {
            throw new ShowTimeException("No showDates found");
        }
        return showTimes;
    }
}
