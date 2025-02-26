package com.banco.clientes.domain.repository;

import com.banco.clientes.domain.entities.ClienteCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteCuentaRepository extends JpaRepository<ClienteCuenta, Long> {
    List<ClienteCuenta> findByClienteId(Long clienteId);

    ClienteCuenta findByCuentaId(Long cuentaId);
    ClienteCuenta findByClienteIdAndCuentaId(Long clienteId, Long cuentaId);
    boolean existsByCuentaId(Long cuentaId);

}
