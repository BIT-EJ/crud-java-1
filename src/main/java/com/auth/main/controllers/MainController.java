package com.auth.main.controllers;

import com.auth.main.dtos.LoginUserDto;
import com.auth.main.exceptions.AuthException;
import com.auth.main.services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {
    private final AuthService authService;

    public MainController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(ModelMap model, @RequestBody LoginUserDto userDto) {
        try {
            authService.loginUser(userDto.getUser(), userDto.getPass());
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Login falhou: " + e.getMessage());
        }
    }
}
