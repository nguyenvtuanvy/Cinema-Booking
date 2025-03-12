package com.example.cinema_booking.repository;

import com.example.cinema_booking.dto.TicketDTO;
import com.example.cinema_booking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
    @Query("select new com.example.cinema_booking.dto.TicketDTO(" +
            "t.id, t.showDate, t.nameChair, t.nameMovie, t.nameRoom, t.totalPrice, st.startTime) " +
            "from Ticket t " +
            "join t.customer c " +
            "join t.showTime st " +
            "where c.id = :customerId")
    Set<TicketDTO> findTicketByCustomerId(@Param("customerId") Long customerId);

    @Modifying
    @Transactional
    @Query("UPDATE Ticket t SET t.status = 0, t.nameChair = '' WHERE t.id = :ticketId")
    void cancelTicket(@Param("ticketId") Long ticketId);
}