package com.auth.main.controllers;

import com.auth.main.entities.User;
import com.auth.main.services.DataService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PanelController {
    private final DataService dataService;

    public PanelController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/panel")
    public ResponseEntity<?> home(ModelMap model, HttpSession session) {
        var id = (Integer) session.getAttribute("logged_in_as");

        if(id == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nao esta logado");
        }

        var data = dataService.findById(id);

        if(data.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro desconhecido");
        }

        User unpacked_data = data.get();

        Map<String, Object> pUser = new HashMap<>();
        pUser.put("id", unpacked_data.getId());
        pUser.put("name", unpacked_data.getName());
        pUser.put("join_date", new Date(unpacked_data.getJoinDate()).toString());

        return ResponseEntity.ok(pUser);
    }
}
