package com.banco.cuentas.infrastructure.controller;

import com.banco.cuentas.application.MovimientoService;
import com.banco.cuentas.domain.entities.Movimiento;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> registrarMovimiento(@RequestBody Movimiento movimiento) {
        try {
            Movimiento nuevoMovimiento = movimientoService.registrarMovimiento(movimiento);
            return ResponseEntity.ok(nuevoMovimiento);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("{\"message\": \"" + e.getMessage() + "\"}");
        }
    }

}
