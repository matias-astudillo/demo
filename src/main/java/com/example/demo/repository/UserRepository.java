package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {  
   /* // Método para guardar un usuario en la base de datos
    User save(User user);*/
    
    // Método para buscar un usuario por su correo electrónico
    User findByCorreo(String correo);
   
   /*  // Método para buscar a todos los usuarios
    List<User> findAll();*/
    
    void deleteById(UUID id);

}