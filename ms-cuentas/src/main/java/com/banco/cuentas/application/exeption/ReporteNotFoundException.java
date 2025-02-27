package com.banco.cuentas.application.exception;

public class ReporteNotFoundException extends RuntimeException {
    public ReporteNotFoundException(String mensaje) {
        super(mensaje);
    }
}
