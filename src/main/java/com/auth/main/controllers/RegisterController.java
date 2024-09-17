package com.auth.main.controllers;

import com.auth.main.dtos.RegisterUserDto;
import com.auth.main.services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {
    private final AuthService authService;

    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(ModelMap model, @RequestBody RegisterUserDto user) {
        try {
            authService.createUser(user.getUser(), user.getPass(), user.getRpass());
            return ResponseEntity.status(HttpStatus.CREATED).body("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registro falhou: " + e.getMessage());
        }
    }
}
