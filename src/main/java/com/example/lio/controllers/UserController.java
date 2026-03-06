package com.example.lio.controllers;

import com.example.lio.models.User;
import com.example.lio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*") // Crucial: Allows your mobile app to talk to the server
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already in Plumber Database!");
        }
        
        // Save the new user
        userRepository.save(user);
        return ResponseEntity.ok("Clearance granted. Record created.");
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User loginRequest) {
        // Find the user by email
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        
        // Check if user exists AND password matches
        if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok(user.get()); // Send user details back to the app
        }
        
        // If it fails, send an unauthorized error
        return ResponseEntity.status(401).body(null);
    }
}