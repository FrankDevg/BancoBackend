package com.banco.cuentas.application.controller;

import com.banco.cuentas.application.service.MovimientoService;
import com.banco.cuentas.domain.entities.Movimiento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {
    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public ResponseEntity<List<Movimiento>> obtenerMovimientos() {
        return ResponseEntity.ok(movimientoService.obtenerMovimientos());
    }

    @PostMapping
    public ResponseEntity<Movimiento> registrarMovimiento(@RequestBody Movimiento movimiento) {
        return ResponseEntity.ok(movimientoService.registrarMovimiento(movimiento));
    }
}
