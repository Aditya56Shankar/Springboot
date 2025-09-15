package com.aditya_shankar.journalApp.controller;

import com.aditya_shankar.journalApp.Entity.User;
import com.aditya_shankar.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class HealthCheck {

    @Autowired
    private UserService userService;
    @GetMapping("/check")
    public String healthCheck() {
        return "OK";

    }
    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.saveNewUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
