package com.first.first.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.first.first.Dto.AuthenticationRequest;
import com.first.first.Utils.JwtUtils;

@RequestMapping("/api/v1")
@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<String> createTokEntity(
            @RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        UserDetails details = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(details);

        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/login")
    public ResponseEntity<String> getMethod() {
        return ResponseEntity.ok("Hello");
    }

}
