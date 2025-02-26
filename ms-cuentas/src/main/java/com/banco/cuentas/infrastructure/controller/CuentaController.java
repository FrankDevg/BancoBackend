package com.banco.cuentas.infrastructure.controller;

import com.banco.cuentas.application.CuentaService;
import com.banco.cuentas.domain.entities.Cuenta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @GetMapping
    public ResponseEntity<List<Cuenta>> obtenerCuentas() {
        return ResponseEntity.ok(cuentaService.obtenerCuentas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerCuentaPorId(@PathVariable("id") Long id) {
        try {
            Optional<Cuenta> cuenta = cuentaService.obtenerCuentaPorId(id);
            return cuenta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
        return ResponseEntity.ok(cuentaService.guardarCuenta(cuenta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable("id") Long id, @RequestBody Cuenta cuentaActualizada) {
        try {
            if (cuentaActualizada == null) {
                return ResponseEntity.badRequest().build();
            }
            Cuenta cuenta = cuentaService.actualizarCuenta(id, cuentaActualizada);
            return ResponseEntity.ok(cuenta);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable("id") Long id) {
        try {
            cuentaService.eliminarCuenta(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
