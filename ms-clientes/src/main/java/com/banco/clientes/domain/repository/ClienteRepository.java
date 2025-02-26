package com.banco.clientes.domain.repository;

import com.banco.clientes.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
