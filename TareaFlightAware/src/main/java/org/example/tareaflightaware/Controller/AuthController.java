package org.example.tareaflightaware.Controller;

import jakarta.validation.Valid;
import org.example.tareaflightaware.DTO.SignInRequest;
import org.example.tareaflightaware.DTO.TokenResponse;
import org.example.tareaflightaware.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> signIn(@Valid @RequestBody SignInRequest request) {
        return ResponseEntity.ok(authService.signIn(request.getEmail(), request.getPassword()));
    }
}
