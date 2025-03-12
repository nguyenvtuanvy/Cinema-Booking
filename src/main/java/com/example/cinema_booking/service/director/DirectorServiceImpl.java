package com.example.cinema_booking.service.director;

import com.example.cinema_booking.entity.Director;
import com.example.cinema_booking.repository.DirectorRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DirectorServiceImpl implements DirectorService{
    private final DirectorRepo directorRepo;

    @Override
    public Optional<Director> findById(Long id) {
        return directorRepo.findById(id);
    }
}
