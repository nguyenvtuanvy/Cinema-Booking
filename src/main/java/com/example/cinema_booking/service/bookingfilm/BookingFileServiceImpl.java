package com.example.cinema_booking.service.bookingfilm;

import com.example.cinema_booking.entity.Customer;
import com.example.cinema_booking.entity.Promotion;
import com.example.cinema_booking.entity.ShowTime;
import com.example.cinema_booking.entity.Ticket;
import com.example.cinema_booking.exception.BookingFilmException;
import com.example.cinema_booking.exception.LoginException;
import com.example.cinema_booking.repository.*;
import com.example.cinema_booking.request.BookingFilmRequest;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookingFileServiceImpl implements BookingFilmService {
    private final TicketRepo ticketRepo;
    private final CustomerRepo customerRepo;
    private final ShowTimeRepo showTimeRepo;
    private final PromotionRepo promotionRepo;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public String BookingFilm(BookingFilmRequest request) throws BookingFilmException, LoginException {
        // Lấy thông tin xác thực từ SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            throw new LoginException("Bạn chưa đăng nhập");
        }

        // Lấy thông tin UserDetails từ Authentication
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // Tìm kiếm Customer từ email trong UserDetails
        String email = userDetails.getUsername();
        Customer customer = customerRepo.findCustomerByEmail(email)
                .orElseThrow(() -> new LoginException("Không tìm thấy thông tin khách hàng"));

        // Kiểm tra email của Customer
        if (customer.getEmail() == null) {
            throw new LoginException("Không thể xác thực email");
        }

        // Lấy thông tin ShowTime từ request
        ShowTime showTime = showTimeRepo.findById(request.getShowtimeId())
                .orElseThrow(() -> new BookingFilmException("Không tìm thấy suất chiếu"));

        // Lấy thông tin Promotion từ request (nếu có)
        Promotion promotion = (request.getPromotionId() != null)
                ? promotionRepo.findById(request.getPromotionId()).orElse(null)
                : null;

        // Đảm bảo Customer được quản lý bởi EntityManager
        customer = entityManager.merge(customer);

        // Tạo đối tượng Ticket
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
                .showDate(request.getShowDate())
                .bookingDate(LocalDate.now())
                .build();

        // Lưu Ticket vào cơ sở dữ liệu
        Ticket savedTicket = ticketRepo.save(ticket);

        // Kiểm tra xem Ticket có được lưu thành công không
        if (savedTicket != null) {
            String qrCodeData = "Mã vé: " + UUID.randomUUID().toString() + "\n"
                    + "Tên phim: " + savedTicket.getNameMovie() + "\n"
                    + "Phòng: " + savedTicket.getNameRoom() + "\n"
                    + "Suất chiếu: " + savedTicket.getShowTime().getStartTime().toString() + "\n"
                    + "Ghế: " + savedTicket.getNameChair() + "\n"
                    + "Giá vé: " + savedTicket.getTotalPrice();

            return generateQRCodeBase64(qrCodeData);
        } else {
            throw new BookingFilmException("Đặt vé xem phim thất bại");
        }
    }




    private String generateQRCodeBase64(String data) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Failed to generate QR code", e);
        }
    }
}