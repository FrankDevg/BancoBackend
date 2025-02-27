package com.banco.clientes.infrastructure.messaging;

import com.banco.clientes.application.service.ClienteService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RabbitMQListener {
    private final ClienteService clienteService;
    private final RabbitMQProducer rabbitMQProducer;

    public RabbitMQListener(ClienteService clienteService, RabbitMQProducer rabbitMQProducer) {
        this.clienteService = clienteService;
        this.rabbitMQProducer = rabbitMQProducer;
    }

    @RabbitListener(queues = "cola.solicitud.cuentas")
    public void recibirSolicitudCuentas(Long clienteId) {
        System.out.println("Recibida solicitud de cuentas para cliente ID: " + clienteId);

        // 🔹 Obtener cuentas del cliente
        List<Long> cuentas = clienteService.obtenerCuentasPorCliente(clienteId);

        // 🔹 Enviar respuesta a la cola `cola.respuesta.cuentas`
        rabbitMQProducer.enviarCuentasCliente(cuentas);
    }
}
