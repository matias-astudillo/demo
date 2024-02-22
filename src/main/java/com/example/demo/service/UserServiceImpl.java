package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;
import java.util.Date;
import java.util.UUID;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.beans.factory.annotation.Value;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${app.validation.email-regex}")
    private String EMAIL_REGEX;

    @Value("${app.validation.password-regex}")
    private String PASSWORD_REGEX;

    @Override
    public User createUser(User user) {
        // Validar el formato del correo electrónico
        if (!Pattern.matches(EMAIL_REGEX, user.getCorreo())) {
            throw new RuntimeException("El formato del correo electrónico no es válido");
        }

        // Validar el formato de la contraseña
        if (!Pattern.matches(PASSWORD_REGEX, user.getContrasena())) {
            throw new RuntimeException("El formato de la contraseña no es válido");
        }

        // Asignar fechas y activo
        Date now = new Date();
        user.setCreado(now);
        user.setModificado(now);
        user.setUltimoLogin(now);
        user.setActivo(true);

        try {
            // Intentar guardar el usuario en el repositorio
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            // Capturar la excepción de violación de unicidad del correo electrónico
            throw new RuntimeException("El correo ya está registrado");
        }
       
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(UUID id, String nombre, String correo) {
        // Verificar si la ID existe
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }

        // Validar el formato del correo electrónico
        if (!Pattern.matches(EMAIL_REGEX, correo)) {
            throw new RuntimeException("El formato del correo electrónico no es válido");
        }

        Date now = new Date();

        User existingUser = userRepository.findById(id).orElse(null);
        existingUser.setNombre(nombre);
        existingUser.setCorreo(correo);
        existingUser.setModificado(now);

        try {
            return userRepository.save(existingUser);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("El correo ya está registrado");
        }
        
    }

    @Override
    public User updatePassword(UUID id, String newPassword) {
        // Verificar si la ID existe
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }

        // Validar el formato de la contraseña
        if (!Pattern.matches(PASSWORD_REGEX, newPassword)) {
            throw new RuntimeException("El formato de la contraseña no es válido");
        }

        Date now = new Date();

        User existingUser = userRepository.findById(id).orElse(null);
        existingUser.setContrasena(newPassword);
        existingUser.setModificado(now);

        return userRepository.save(existingUser);
    }



}