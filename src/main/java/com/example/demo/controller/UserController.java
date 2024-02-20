package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.ErrorInfo;
import com.example.demo.model.UserResponseDTO;
import com.example.demo.service.UserService;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User newUser = userService.createUser(user);
            UserResponseDTO responseDTO = new UserResponseDTO(newUser.getId(), newUser.getCreado(), newUser.getModificado(), newUser.getUltimoLogin(), newUser.isActivo());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (RuntimeException e) {
            ErrorInfo errorInfo = new ErrorInfo(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);
        }
    }
}