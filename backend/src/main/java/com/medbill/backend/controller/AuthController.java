package com.medbill.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Security and session endpoints")
public class AuthController {

    @PostMapping("/login")
    @Operation(summary = "Login to the platform")
    public ResponseEntity<Map<String, Object>> login() {
        return ResponseEntity.ok(Map.of(
                "accessToken", "demo-access-token",
                "refreshToken", "demo-refresh-token",
                "tokenType", "Bearer",
                "expiresIn", 3600,
                "user", Map.of("id", "demo-user", "role", "SUPER_ADMIN")
        ));
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh() {
        return ResponseEntity.ok(Map.of("accessToken", "refreshed-demo-access-token"));
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Map<String, String>> forgotPassword() {
        return ResponseEntity.ok(Map.of("message", "Password reset email sent"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword() {
        return ResponseEntity.ok(Map.of("message", "Password reset successful"));
    }
}
