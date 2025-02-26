package com.banco.cuentas.domain.repository;

import com.banco.cuentas.domain.entities.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByFechaBetweenAndCuentaIdIn(LocalDateTime fechaInicio, LocalDateTime fechaFin, List<Long> cuentaIds);
}
