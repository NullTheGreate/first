package com.first.first.jwt;

import com.first.first.Utils.JwtUtils;
import com.first.first.entity.User;
import com.first.first.service.CustomUserService;
import com.first.first.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@EnableWebSecurity
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtUtils jwtUtils;

    private CustomUserService customUserService;

    private UserService userService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, CustomUserService customUserService,
            UserService userService) {
        this.jwtUtils = jwtUtils;
        this.customUserService = customUserService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        try {

            String jwt = getJwtFromRequest((HttpServletRequest) request);

            if (jwt != null && jwtUtils.validateToken(jwt)) {
                String username = jwtUtils.extractUsername(jwt);
                // UserDetails userDetails = customUserService.loadUserByUsername(username);
                UserDetails userDetails = userService.findByUsernameWithRoles(username);
                // UserDetails userDetails = new ;
                UsernamePasswordAuthenticationToken jwtAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null,
                                userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(jwtAuthenticationToken);
            }
        } catch (UsernameNotFoundException ex) {
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            servletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
            servletResponse.getWriter().write("Unauthorized: " + ex.getMessage());
            return;
        }

        filterChain.doFilter(request, response);
    }


    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }



}
