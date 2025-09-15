package com.aditya_shankar.journalApp.controller;

import com.aditya_shankar.journalApp.Entity.User;
import com.aditya_shankar.journalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @GetMapping("/check")
    public String healthCheck() {
        return "OK";

    }
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUser(){
        List<User> all=userService.getAll();
        if(all!=null&&!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin")
    public void createAdmin(@RequestBody User user){
        userService.saveAdmin(user);
    }
}
