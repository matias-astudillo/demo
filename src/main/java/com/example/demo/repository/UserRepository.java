package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {  
   /* // Método para guardar un usuario en la base de datos
    User save(User user);*/
    
    // Método para buscar un usuario por su correo electrónico
    User findByCorreo(String correo);
   
   /*  // Método para buscar a todos los usuarios
    List<User> findAll();*/
    
}