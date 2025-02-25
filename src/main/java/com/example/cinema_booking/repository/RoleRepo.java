package com.example.cinema_booking.repository;

import com.example.cinema_booking.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
