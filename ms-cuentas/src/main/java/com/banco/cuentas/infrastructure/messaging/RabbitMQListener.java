package com.banco.cuentas.infrastructure.rabbitmq;

import com.banco.cuentas.application.service.ReporteService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RabbitMQListener {

    private final ReporteService reporteService;
    private final ObjectMapper objectMapper;

    public RabbitMQListener(ReporteService reporteService, ObjectMapper objectMapper) {
        this.reporteService = reporteService;
        this.objectMapper = objectMapper;
    }

    //  Escuchar la respuesta desde `ms-clientes`
    @RabbitListener(queues = "cola.respuesta.cuentas")
    public void recibirCuentasCliente(String mensaje) {
        try {
            //  Convertir el mensaje JSON a `List<Long>`
            List<Long> cuentasCliente = objectMapper.readValue(mensaje, new TypeReference<List<Long>>() {});

            System.out.println("Recibida lista de cuentas: " + cuentasCliente);
            reporteService.setCuentasClienteFuture(cuentasCliente);
        } catch (Exception e) {
            System.err.println("Error al procesar el mensaje: " + e.getMessage());
        }
    }
}
