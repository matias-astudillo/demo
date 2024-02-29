package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import com.example.demo.model.User;
import com.example.demo.model.ErrorInfo;
import com.example.demo.service.UserService;
import com.example.demo.model.UserUpdateDTO;
import com.example.demo.controller.UserController;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class DemoApplicationTests {

	@Autowired
    private UserService userService;

    @Autowired
    private UserController userController;

	@Test
	void contextLoads() {
		System.out.println("Hola, mundo!");
	
        User newUser = new User();
        newUser.setNombre("John");
        newUser.setCorreo("john@gmail.com");
        newUser.setContrasena("password123");

        ResponseEntity<?> responseEntity = userController.createUser(newUser);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

    @Test
	void errorCorreo() {
		System.out.println("Hola, mundo!");
		 // Arrange
        User newUser = new User();
        newUser.setNombre("John");
        newUser.setCorreo("johngmailcom");
        newUser.setContrasena("password123");

        ResponseEntity<?> responseEntity = userController.createUser(newUser);
        Object responseBody = responseEntity.getBody();
        ErrorInfo errorinfo = (ErrorInfo) responseBody;
        String mensaje = errorinfo.getMessage();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("El formato del correo electrónico no es válido", mensaje);
	}

    @Test
	void errorPass() {
		System.out.println("Hola, mundo!");
		 // Arrange
        User newUser = new User();
        newUser.setNombre("John");
        newUser.setCorreo("john23@gmail.com");
        newUser.setContrasena("pass");

        ResponseEntity<?> responseEntity = userController.createUser(newUser);
        Object responseBody = responseEntity.getBody();
        ErrorInfo errorinfo = (ErrorInfo) responseBody;
        String mensaje = errorinfo.getMessage();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("El formato de la contraseña no es válido", mensaje);
	}

    //Updateuser

    @Test
	void updateUserCorrect() {
		System.out.println("Hola, mundo!");
		
        UUID userId = UUID.fromString("611b68fc-dfd3-4c7b-9ceb-666e11e677be");
        UserUpdateDTO userUp = new UserUpdateDTO();
        userUp.setNombre("NuevoNombre");
        userUp.setCorreo("nuevo@gmail.com");

        ResponseEntity<?> responseEntity = userController.updateUser(userId, userUp);
        Object responseBody = responseEntity.getBody();
        User user = (User) responseBody;

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("NuevoNombre", user.getNombre());
        assertEquals("nuevo@gmail.com", user.getCorreo());
	}

    @Test
	void updateUserBadId() {
		System.out.println("Hola, mundo!");
		
        UUID userId = UUID.fromString("7854b132-426d-4294-a156-aeb1dcb7d0cd");
        UserUpdateDTO userUp = new UserUpdateDTO();
        userUp.setNombre("NuevoNombre");
        userUp.setCorreo("nuevo@gmail.com");

        ResponseEntity<?> responseEntity = userController.updateUser(userId, userUp);
        Object responseBody = responseEntity.getBody();
        ErrorInfo errorinfo = (ErrorInfo) responseBody;
        String mensaje = errorinfo.getMessage();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("User not found with id: " + userId, mensaje);
	}

    @Test
	void updateUserBadCorreo() {
		System.out.println("Hola, mundo!");
		
        UUID userId = UUID.fromString("611b68fc-dfd3-4c7b-9ceb-666e11e677be");
        UserUpdateDTO userUp = new UserUpdateDTO();
        userUp.setNombre("NuevoNombre");
        userUp.setCorreo("nuevogmailss");

        ResponseEntity<?> responseEntity = userController.updateUser(userId, userUp);
        Object responseBody = responseEntity.getBody();
        ErrorInfo errorinfo = (ErrorInfo) responseBody;
        String mensaje = errorinfo.getMessage();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("El formato del correo electrónico no es válido", mensaje);
	}

    //UpdatePassword
   
    @Test
	void updatePasswordCorrectTrue() {
		System.out.println("Hola, mundo!");
		
        UUID userId = UUID.fromString("611b68fc-dfd3-4c7b-9ceb-666e11e677be");
        String newPass = "Pass55556";

        ResponseEntity<?> responseEntity = userController.updatePassword(userId, newPass);
        Object responseBody = responseEntity.getBody();
        User user = (User) responseBody;
        String password = user.getContrasena();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(newPass, password);
	}

    @Test
	void updatePasswordErrorPass() {
		System.out.println("Hola, mundo!");
		
        UUID userId = UUID.fromString("611b68fc-dfd3-4c7b-9ceb-666e11e677be");
        String newPassword = "123";
        String expectedErrorMessage = "El formato de la contraseña no es válido";

        ResponseEntity<?> responseEntity = userController.updatePassword(userId, newPassword);
        Object responseBody = responseEntity.getBody();
        ErrorInfo errorinfo = (ErrorInfo) responseBody;
        String mensaje = errorinfo.getMessage();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(expectedErrorMessage, mensaje);
	}

    @Test
	void updatePasswordErrorId() {
		System.out.println("Hola, mundo!");
		
        UUID userId = UUID.fromString("7854b132-426d-4294-a156-aeb1dcb7d0cd");
        String newPassword = "1234password";
        String expectedErrorMessage = "User not found with id: " + userId;

        ResponseEntity<?> responseEntity = userController.updatePassword(userId, newPassword);
        Object responseBody = responseEntity.getBody();
        ErrorInfo errorinfo = (ErrorInfo) responseBody;
        String mensaje = errorinfo.getMessage();
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(expectedErrorMessage, mensaje);
	}

    @Test
	void getUsers() {
		System.out.println("Hola, mundo!");
		
        ResponseEntity<?> responseEntity = userController.getAllUsers();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

    //Deleteuser

    @Test
	void delete() {
		System.out.println("Hola, mundo!");

        UUID userId = UUID.fromString("611b68fc-dfd3-4c7b-9ceb-666e11e677be");
        ResponseEntity<?> responseEntity = userController.deleteUser(userId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

}
