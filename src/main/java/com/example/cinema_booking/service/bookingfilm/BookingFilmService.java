package com.example.cinema_booking.service.bookingfilm;

import com.example.cinema_booking.exception.BookingFilmException;
import com.example.cinema_booking.exception.LoginException;
import com.example.cinema_booking.request.BookingFilmRequest;

public interface BookingFilmService {
    String BookingFilm(BookingFilmRequest bookingFilmRequest) throws BookingFilmException, LoginException;
}
