package com.example.demo.service;

import com.example.demo.model.User;

public interface UserService {
    User createUser(User user);

    void deleteUser(Long id);

    User updateUser(Long id, String nombre, String correo);
}