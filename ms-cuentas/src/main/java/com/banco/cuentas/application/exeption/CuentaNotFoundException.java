package com.banco.cuentas.application.exception;

public class CuentaNotFoundException extends RuntimeException {
    public CuentaNotFoundException(String mensaje) {
        super(mensaje);
    }
}
