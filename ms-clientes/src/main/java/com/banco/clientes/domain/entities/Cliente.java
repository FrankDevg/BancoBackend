package com.banco.clientes.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Clientes")
public class Cliente extends Persona {
    private String contrasenia;
    private Boolean estado;

    // 🔹 Constructor vacío
    public Cliente() {}

    // 🔹 Constructor con parámetros
    public Cliente(Long id, String nombre, String genero, Integer edad, String identificacion, String direccion,
                   String telefono, String contrasenia, Boolean estado) {
        super(id, nombre, genero, edad, identificacion, direccion, telefono);
        this.contrasenia = contrasenia;
        this.estado = estado;
    }

    // 🔹 Getters y Setters
    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }
}
