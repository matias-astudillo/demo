package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$";

    @Override
    public User createUser(User user) {
        // Validaciones de correo y contraseña
        if (userRepository.findByCorreo(user.getCorreo()) != null) {
            throw new RuntimeException("El correo ya está registrado");
        }

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

        // Aquí se agrega el usuario en el repositorio
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, String nombre, String correo) {
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

        return userRepository.save(existingUser);
    }

    @Override
    public User updatePassword(Long id, String newPassword) {
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