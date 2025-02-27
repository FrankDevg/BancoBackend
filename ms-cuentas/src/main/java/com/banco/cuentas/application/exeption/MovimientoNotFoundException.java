package com.banco.cuentas.application.exception;

public class MovimientoNotFoundException extends RuntimeException {
    public MovimientoNotFoundException(String mensaje) {
        super(mensaje);
    }
}
