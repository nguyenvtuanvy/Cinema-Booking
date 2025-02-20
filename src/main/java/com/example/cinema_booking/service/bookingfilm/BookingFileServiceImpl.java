package com.example.cinema_booking.service.bookingfilm;

import com.example.cinema_booking.entity.Customer;
import com.example.cinema_booking.entity.Promotion;
import com.example.cinema_booking.entity.ShowTime;
import com.example.cinema_booking.entity.Ticket;
import com.example.cinema_booking.exception.BookingFilmException;
import com.example.cinema_booking.exception.LoginException;
import com.example.cinema_booking.repository.*;
import com.example.cinema_booking.request.BookingFilmRequest;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;

@Service
@AllArgsConstructor
public class BookingFileServiceImpl implements BookingFilmService {
    private final TicketRepo ticketRepo;
    private final ShowTimeRepo showTimeRepo;
    private final PromotionRepo promotionRepo;
    private final EntityManager entityManager;
    @Override
    @Transactional
    public String BookingFilm(BookingFilmRequest request) throws BookingFilmException, LoginException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Customer customer = (Customer) authentication.getPrincipal();
            if (customer.getEmail() != null) {
                ShowTime showTime = showTimeRepo.findById(request.getShowtimeId()).orElseThrow();

                Promotion promotion = (request.getPromotionId() != null)
                        ? promotionRepo.findById(request.getPromotionId()).orElse(null)
                        : null;

                customer = entityManager.merge(customer);

                Ticket ticket = Ticket.builder()
                        .customer(customer)
                        .showTime(showTime)
                        .promotion(promotion)
                        .nameCustomer(customer.getFullName())
                        .nameMovie(request.getNameMovie())
                        .nameRoom(request.getNameRoom())
                        .nameChair(request.getNameChair())
                        .numberChair(request.getNumberChair())
                        .totalPrice(request.getTotalPrice())
                        .bookingDate(LocalDate.now())
                        .build();

                Ticket saveTicket = ticketRepo.save(ticket);

                if (saveTicket != null){
                    return "Đặt vé xem phim thành công";
                } else {
                    throw new BookingFilmException("Đặt vé xem phim thất bại");
                }
            } else {
                throw new LoginException("Không thể xác thực email");
            }
        } else {
            throw new LoginException("Bạn chưa đăng nhập");
        }
    }
}
