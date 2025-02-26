package com.banco.cuentas.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCuenta;
    private String tipoCuenta; // "Ahorro" o "Corriente"
    private Double saldoInicial;
    private Boolean estado;

    // 🔹 Constructor vacío
    public Cuenta() {}

    // 🔹 Constructor con parámetros
    public Cuenta(Long id, String numeroCuenta, String tipoCuenta, Double saldoInicial, Boolean estado) {
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
    }

    // 🔹 Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }

    public String getTipoCuenta() { return tipoCuenta; }
    public void setTipoCuenta(String tipoCuenta) { this.tipoCuenta = tipoCuenta; }

    public Double getSaldoInicial() { return saldoInicial; }
    public void setSaldoInicial(Double saldoInicial) { this.saldoInicial = saldoInicial; }

    public Boolean getEstado() { return estado; }
    public void setEstado(Boolean estado) { this.estado = estado; }
}
