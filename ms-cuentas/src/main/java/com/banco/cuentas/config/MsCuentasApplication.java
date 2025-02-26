package com.banco.cuentas.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.banco.cuentas")
@EntityScan(basePackages = "com.banco.cuentas.domain.entities")
@EnableJpaRepositories(basePackages = "com.banco.cuentas.domain.repository")
public class MsCuentasApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsCuentasApplication.class, args);
    }
}
