package com.banco.clientes.application.exception;

public class CuentasNoEncontradasException extends RuntimeException {
    public CuentasNoEncontradasException(String mensaje) {
        super(mensaje);
    }
}
