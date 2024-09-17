package com.auth.main.controllers;

import com.auth.main.dtos.EditUserDto;
import com.auth.main.services.AuthService;
import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EditController {
    private final AuthService authService;

    public EditController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/edit")
    public ResponseEntity<String> edit(HttpSession session, @RequestBody EditUserDto data) {
        var id = (Integer)session.getAttribute("logged_in_as");

        if(id == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("failure");
        }

        try {
            authService.editUser(id, data.getPass(), data.getRPass());
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Edicao falhou: " + e.getMessage());
        }
    }
}
