package com.example.cinema_booking.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws IOException {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // Kiểm tra xem người dùng đã xác thực chưa
            if ((authentication != null) && !(authentication instanceof AnonymousAuthenticationToken)) {
                // Kiểm tra quyền truy cập cho các đường dẫn bắt đầu bằng "/manager"
                if (request.getServletPath().startsWith("/manager") &&
                        authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập vào trang này");
                    return;
                }

                // Kiểm tra quyền truy cập cho các đường dẫn bắt đầu bằng "/customer"
                if (request.getServletPath().startsWith("/customer") &&
                        authentication.getAuthorities().stream().noneMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bạn không có quyền truy cập vào trang này");
                    return;
                }
            }

            // Chuyển request đến filter tiếp theo
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            logger.error("Lỗi kiểm tra quyền truy cập: {}", e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lỗi kiểm tra quyền truy cập: " + e.getMessage());
        }
    }
}