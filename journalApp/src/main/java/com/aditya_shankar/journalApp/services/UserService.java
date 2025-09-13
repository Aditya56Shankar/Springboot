package com.aditya_shankar.journalApp.services;

import com.aditya_shankar.journalApp.Entity.User;
import com.aditya_shankar.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(User user) {
        user.setPass(passwordEncoder.encode(user.getPass()));
        user.setRoles(Arrays.asList("USER"));
        userRepo.save(user);
    }
    public void saveNewUser(User user) {

        userRepo.save(user);
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userRepo.findById(id);
    }

    public void deleteById(ObjectId id) {
        userRepo.deleteById(id);
    }

    public User findByUserName(String username) {
        return userRepo.findByUsername(username);
    }
}
