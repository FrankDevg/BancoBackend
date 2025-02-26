package com.banco.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private static final String EXCHANGE_NAME = "banco.exchange";

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Queue queueSolicitudCuentas() {
        return new Queue("cola.solicitud.cuentas", true);
    }

    @Bean
    public Queue queueRespuestaCuentas() {
        return new Queue("cola.respuesta.cuentas", true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingSolicitudCuentas(Queue queueSolicitudCuentas, DirectExchange exchange) {
        return BindingBuilder.bind(queueSolicitudCuentas).to(exchange).with("solicitud.cuentas");
    }

    @Bean
    public Binding bindingRespuestaCuentas(Queue queueRespuestaCuentas, DirectExchange exchange) {
        return BindingBuilder.bind(queueRespuestaCuentas).to(exchange).with("respuesta.cuentas");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
