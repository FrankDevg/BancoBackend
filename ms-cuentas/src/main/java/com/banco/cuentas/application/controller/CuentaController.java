package com.banco.cuentas.application.controller;

import com.banco.cuentas.application.service.CuentaService;
import com.banco.cuentas.domain.entities.Cuenta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    // 🔹 Obtener todas las cuentas
    @GetMapping
    public ResponseEntity<List<Cuenta>> obtenerCuentas() {
        return ResponseEntity.ok(cuentaService.obtenerCuentas());
    }

    // 🔹 Obtener cuenta por ID (lanza excepción si no existe)
    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> obtenerCuentaPorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(cuentaService.obtenerCuentaPorId(id));
    }

    // 🔹 Crear una cuenta nueva
    @PostMapping
    public ResponseEntity<Cuenta> crearCuenta(@RequestBody Cuenta cuenta) {
        return ResponseEntity.ok(cuentaService.guardarCuenta(cuenta));
    }

    // 🔹 Actualizar cuenta existente
    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> actualizarCuenta(@PathVariable("id") Long id, @RequestBody Cuenta cuentaActualizada) {
        return ResponseEntity.ok(cuentaService.actualizarCuenta(id, cuentaActualizada));
    }

    // 🔹 Eliminar cuenta existente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable("id") Long id) {
        cuentaService.eliminarCuenta(id);
        return ResponseEntity.noContent().build();
    }

}
