package com.banco.clientes.infrastructure.messaging;

import com.banco.clientes.application.ClienteService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

@Component
public class RabbitMQListener {
    private final ClienteService clienteService;
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQListener(ClienteService clienteService, RabbitTemplate rabbitTemplate) {
        this.clienteService = clienteService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = "cola.solicitud.cuentas")
    public void recibirSolicitudCuentas(Long clienteId) {
        System.out.println("📩 Recibida solicitud de cuentas para cliente ID: " + clienteId);

        // 🔹 Obtener cuentas del cliente
        List<Long> cuentas = clienteService.obtenerCuentasPorCliente(clienteId);

        // 🔹 Convertir la lista en JSON antes de enviarla
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonCuentas = objectMapper.writeValueAsString(cuentas);

            // 🔹 Enviar respuesta a `cola.respuesta.cuentas`
            rabbitTemplate.convertAndSend("cola.respuesta.cuentas", jsonCuentas);
            System.out.println("✅ Respuesta enviada con cuentas: " + jsonCuentas);
        } catch (Exception e) {
            System.err.println("❌ Error al enviar el mensaje: " + e.getMessage());
        }
    }

}
