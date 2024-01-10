package com.fransica.cardscheme.controller;

import com.fransica.cardscheme.dto.request.AuthRequest;
import com.fransica.cardscheme.dto.request.UserRegistrationDto;
import com.fransica.cardscheme.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<?> userSignUp(@Valid @RequestBody UserRegistrationDto userRegistrationDto) {

        return new ResponseEntity<>(authService.createUser(userRegistrationDto),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        return new ResponseEntity<>(authService.login(authRequest), HttpStatus.OK);
    }
}
