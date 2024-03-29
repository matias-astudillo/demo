package com.example.demo.model;

import com.example.demo.model.Telefono;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.UUID; 
import io.swagger.v3.oas.annotations.Hidden;

@Entity(name = "usuario")
public class User {

    @Id
    @Hidden
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @Size(max = 20, message = "nombre muy largo")
    private String nombre;

    @Column(unique = true)
    private String correo;
    
    private String contrasena;

    @ElementCollection
    private List<Telefono> telefonos;

    @Hidden
    private Date creado;

    @Hidden
    private Date modificado;

    @Hidden
    private Date ultimoLogin;

    @Hidden
    private boolean activo;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public Date getModificado() {
        return modificado;
    }

    public void setModificado(Date modificado) {
        this.modificado = modificado;
    }

    public Date getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(Date ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}