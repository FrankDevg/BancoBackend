package com.banco.clientes.application.exception;

public class ClienteNotFoundException extends RuntimeException {
    public ClienteNotFoundException(String mensaje) {
        super(mensaje);
    }
}
