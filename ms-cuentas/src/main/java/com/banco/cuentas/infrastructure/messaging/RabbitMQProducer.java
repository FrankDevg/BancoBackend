package com.banco.cuentas.infrastructure.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // ✅ Enviar solicitud de cuentas por cliente
    public void solicitarCuentasCliente(Long clienteId) {
        System.out.println("📩 Enviando solicitud de cuentas para cliente ID: " + clienteId);
        rabbitTemplate.convertAndSend("cola.solicitud.cuentas", clienteId);
    }

}
