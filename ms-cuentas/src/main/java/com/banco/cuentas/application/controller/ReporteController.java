package com.banco.cuentas.application.controller;

import com.banco.cuentas.application.exception.ReporteNotFoundException;
import com.banco.cuentas.application.service.ReporteService;
import com.banco.cuentas.domain.entities.Movimiento;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping
    public ResponseEntity<List<Movimiento>> obtenerReporteEstadoCuenta(
            @RequestParam("clienteId") Long clienteId,
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin) {

        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin son obligatorias.");
        }

        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser mayor que la fecha de fin.");
        }

        List<Movimiento> reporte = reporteService.obtenerReporteEstadoCuenta(clienteId, fechaInicio, fechaFin);

        if (reporte.isEmpty()) {
            return ResponseEntity.noContent().build();
        }


        return ResponseEntity.ok(reporte);
    }
}
