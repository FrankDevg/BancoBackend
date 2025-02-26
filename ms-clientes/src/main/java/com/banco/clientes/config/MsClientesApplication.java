package com.banco.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.banco.clientes")
@EntityScan(basePackages = "com.banco.clientes.domain.entities")
@EnableJpaRepositories(basePackages = "com.banco.clientes.domain.repository")
public class MsClientesApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsClientesApplication.class, args);
    }
}
