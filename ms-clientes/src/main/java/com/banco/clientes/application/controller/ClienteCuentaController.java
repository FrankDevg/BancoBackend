package com.banco.clientes.application.controller;

import com.banco.clientes.application.service.ClienteService;
import com.banco.clientes.domain.entities.ClienteCuenta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes/cuentas")
public class ClienteCuentaController {
    private final ClienteService clienteService;

    public ClienteCuentaController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Obtener todas las cuentas asignadas a un cliente
    @GetMapping("/{clienteId}")
    public ResponseEntity<List<Long>> obtenerCuentasPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(clienteService.obtenerCuentasPorCliente(clienteId));
    }

    //  Asignar una cuenta a un cliente (Evita duplicados)
    @PostMapping
    public ResponseEntity<ClienteCuenta> asignarCuentaACliente(@RequestBody ClienteCuenta clienteCuenta) {
        return ResponseEntity.ok(clienteService.asignarCuentaACliente(clienteCuenta.getClienteId(), clienteCuenta.getCuentaId()));
    }


    //  Eliminar una cuenta asignada a un cliente
    @DeleteMapping
    public ResponseEntity<?> eliminarCuentaDeCliente(@RequestParam Long clienteId, @RequestParam Long cuentaId) {
        try {
            clienteService.eliminarCuentaDeCliente(clienteId, cuentaId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }


}
