package com.banco.clientes.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente_cuenta")
public class ClienteCuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long clienteId;
    private Long cuentaId;

    public ClienteCuenta() {}

    public ClienteCuenta(Long clienteId, Long cuentaId) {
        this.clienteId = clienteId;
        this.cuentaId = cuentaId;
    }

    public Long getId() { return id; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public Long getCuentaId() { return cuentaId; }
    public void setCuentaId(Long cuentaId) { this.cuentaId = cuentaId; }
}
