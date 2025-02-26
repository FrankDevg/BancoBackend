package com.banco.cuentas.domain.repository;

import com.banco.cuentas.domain.entities.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}
