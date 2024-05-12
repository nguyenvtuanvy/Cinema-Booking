package com.example.cinema_booking.filter;

import com.example.cinema_booking.exception.AuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws IOException {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if ((authentication != null) && !(authentication instanceof AnonymousAuthenticationToken)){
                if (request.getServletPath().startsWith(("/manager"))){
                    boolean checkRole = authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
                    if (checkRole){
                        filterChain.doFilter(request, response);
                        return;
                    } else {
                        SecurityContextHolder.getContext().setAuthentication(null);
                        response.setStatus(401);
                        throw new AuthenticationException("Bạn không có quyền truy cập vào trang này!");
                    }
                }
            }
            filterChain.doFilter(request, response);
        }catch (Exception e){
            response.setStatus(401);
            response.getWriter().write(e.getMessage());
        }
    }
}
