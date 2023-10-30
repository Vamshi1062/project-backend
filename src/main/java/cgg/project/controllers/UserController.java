package cgg.project.controllers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cgg.project.entities.User;
import cgg.project.repositories.UserRepository;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")

public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

     
    @PostMapping("/register")
public ResponseEntity<String> registerUser(@RequestBody User user) {
    // Check if the email is already in use
    User existingUserByEmail = userRepository.findByEmail(user.getEmail());
    if (existingUserByEmail != null) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
    }

    // Check if the username is already in use
    User existingUserByUsername = userRepository.findByUserName(user.getUserName());
    if (existingUserByUsername != null) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already in use");
    }

    // Both email and username are unique, so save the user
    userRepository.save(user);
    
    // Respond with a success message or the saved user details
    return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
}

}
