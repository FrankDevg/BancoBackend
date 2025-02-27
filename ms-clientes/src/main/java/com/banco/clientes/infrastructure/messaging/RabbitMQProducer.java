package com.banco.clientes.infrastructure.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

@Component
public class RabbitMQProducer {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void enviarCuentasCliente(List<Long> cuentas) {
        try {
            String jsonCuentas = objectMapper.writeValueAsString(cuentas);
            rabbitTemplate.convertAndSend("cola.respuesta.cuentas", jsonCuentas);
            System.out.println("✅ Respuesta enviada con cuentas: " + jsonCuentas);
        } catch (Exception e) {
            System.err.println("❌ Error al enviar el mensaje a RabbitMQ: " + e.getMessage());
        }
    }
}
