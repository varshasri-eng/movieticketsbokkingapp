package com.booking.movieticket.controller;

import com.booking.movieticket.model.User;
import com.booking.movieticket.repository.UserRepository;
import com.booking.movieticket.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✅ SIGN UP (Register)
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole("ROLE_USER");
        }

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        final UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(),
                userRepository.findByEmail(user.getEmail()).getRole().equals("ROLE_ADMIN") ?
                        java.util.Collections.singleton(() -> "ROLE_ADMIN") :
                        java.util.Collections.singleton(() -> "ROLE_USER")
        );

        String token = jwtUtil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(token);
    }
}
