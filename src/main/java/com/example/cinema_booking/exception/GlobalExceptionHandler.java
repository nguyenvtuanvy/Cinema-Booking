package com.example.cinema_booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MovieException.class)
    public ResponseEntity<?> handleMovieException(MovieException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal server error occurred: " + e.getMessage());
    }

//    @ExceptionHandler(AccessDeniedException.class)
//    public final ResponseEntity<String> handleAccessDeniedException(AccessDeniedException ex) {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bạn không có quyền truy cập");
//    }
}
