package com.banco.cuentas.application.service;

import com.banco.cuentas.domain.entities.Movimiento;
import com.banco.cuentas.domain.repository.MovimientoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ReporteService {
    private final MovimientoRepository movimientoRepository;
    private final RabbitTemplate rabbitTemplate;
    private CompletableFuture<List<Long>> cuentasClienteFuture;

    public ReporteService(MovimientoRepository movimientoRepository, RabbitTemplate rabbitTemplate) {
        this.movimientoRepository = movimientoRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Movimiento> obtenerReporteEstadoCuenta(Long clienteId, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        cuentasClienteFuture = new CompletableFuture<>();
        rabbitTemplate.convertAndSend("cola.solicitud.cuentas", clienteId);

        List<Long> cuentasCliente;
        try {
            cuentasCliente = cuentasClienteFuture.get(); // Espera hasta recibir la respuesta
        } catch (Exception e) {
            throw new RuntimeException(" Error al obtener cuentas del cliente: " + e.getMessage());
        }

        if (cuentasCliente.isEmpty()) {
            return List.of();
        }

        return movimientoRepository.findByFechaBetweenAndCuentaIdIn(fechaInicio, fechaFin, cuentasCliente);
    }
    public void setCuentasClienteFuture(List<Long> cuentas) {
        if (cuentasClienteFuture != null) {
            System.out.println("📩 Respuesta recibida y procesada: " + cuentas);
            cuentasClienteFuture.complete(cuentas);
        }
    }


}
