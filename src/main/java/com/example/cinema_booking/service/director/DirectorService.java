package com.example.cinema_booking.service.director;

import com.example.cinema_booking.entity.Director;

import java.util.Optional;

public interface DirectorService {
    Optional<Director> findById(Long id);
}
