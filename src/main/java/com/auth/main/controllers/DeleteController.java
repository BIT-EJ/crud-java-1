package com.auth.main.controllers;

import com.auth.main.services.DataService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteController {
    private final DataService dataService;

    public DeleteController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/delete")
    public String delete(HttpSession session) {
        var id = (Integer)session.getAttribute("logged_in_as");

        if(id != null){
            dataService.deleteById(id);
        }

        return "success";
    }
}
