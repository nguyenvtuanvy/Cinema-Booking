package com.example.cinema_booking.service.chair;

import com.example.cinema_booking.dto.ChairDTO;
import com.example.cinema_booking.dto.RoomWithGroupedChairsDTO;
import com.example.cinema_booking.exception.ChairException;
import com.example.cinema_booking.repository.ChairRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class ChairServiceImpl implements ChairService{
    private final ChairRepository chairRepository;


    @Override
    public RoomWithGroupedChairsDTO getChairInfoByMovieAndShowDateAndShowTime(Long movieId, Long showDateId, Long showTimeId) throws ChairException {
        Set<ChairDTO> chairs = chairRepository.findByMovieAndShowDateAndShowTime(movieId, showDateId, showTimeId);

        if (chairs.isEmpty()) {
            throw new ChairException("Không tìm thấy ghế nào cho phim, ngày chiếu và giờ chiếu đã chọn.");
        }

        return RoomWithGroupedChairsDTO.from(chairs);
    }
}
