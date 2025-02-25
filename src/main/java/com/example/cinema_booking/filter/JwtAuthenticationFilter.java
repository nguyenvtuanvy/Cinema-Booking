package com.example.cinema_booking.filter;

import com.example.cinema_booking.service.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    // Logger để ghi log
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        try {
            // Kiểm tra header Authorization
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                logger.debug("Không tìm thấy JWT token trong header Authorization");
                filterChain.doFilter(request, response);
                return;
            }

            // Trích xuất JWT token từ header
            jwt = authHeader.substring(7);
            userEmail = jwtService.extractUsername(jwt);

            // Xác thực token và thiết lập SecurityContext
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken); // Thiết lập SecurityContext
                    logger.debug("Xác thực thành công cho người dùng: {}", userEmail);
                } else {
                    logger.warn("JWT token không hợp lệ cho người dùng: {}", userEmail);
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token không hợp lệ");
                    return;
                }
            }
        } catch (Exception e) {
            logger.error("Lỗi xác thực JWT: {}", e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lỗi xác thực: " + e.getMessage());
            return;
        }

        // Chuyển request đến filter tiếp theo
        filterChain.doFilter(request, response);
    }
}