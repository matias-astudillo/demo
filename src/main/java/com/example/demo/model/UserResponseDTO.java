package com.example.demo.model;

import java.util.Date;
import java.util.UUID;

public class UserResponseDTO {
    private UUID id;
    private Date creado;
    private Date modificado;
    private Date ultimoLogin;
    private boolean activo;

    public UserResponseDTO(UUID id, Date creado, Date modificado, Date ultimoLogin, boolean activo) {
        this.id = id;
        this.creado = creado;
        this.modificado = modificado;
        this.ultimoLogin = ultimoLogin;
        this.activo = activo;
    }
/*
    public UUID getId() {
        return id;
    }

    public Date getCreado() {
        return creado;
    }

    public Date getModificado() {
        return modificado;
    }

    public Date getUltimoLogin() {
        return ultimoLogin;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public void setModificado(Date modificado) {
        this.modificado = modificado;
    }

    public void setUltimoLogin(Date ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }*/
}