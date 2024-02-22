package com.example.demo.service;

import com.example.demo.model.User;
import java.util.UUID;

public interface UserService {
    User createUser(User user);

    void deleteUser(UUID id);

    User updateUser(UUID id, String nombre, String correo);

    User updatePassword(UUID id, String newPassword);
}