package com.efpm.controller;

import com.efpm.model.User;
import com.efpm.service.JwtService;
import com.efpm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8081")
public class AuthController {

    @Autowired private AuthenticationManager authManager;
    @Autowired private UserService userService;
    @Autowired private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email and password required"));
        }
        User saved = userService.register(user);
        // Do not return password back to client
        saved.setPassword(null);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
        String email = req.get("email");
        String password = req.get("password");
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            UserDetails user = userService.loadUserByUsername(email);
            String token = jwtService.generateToken(user);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(Map.of("error", "Authentication failed", "details", ex.getMessage()));
        }
    }
}
