package com.banco.cuentas.application;

import com.banco.cuentas.domain.entities.Movimiento;
import com.banco.cuentas.domain.entities.Cuenta;
import com.banco.cuentas.domain.repository.MovimientoRepository;
import com.banco.cuentas.domain.repository.CuentaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {
    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    public List<Movimiento> obtenerMovimientos() {
        return movimientoRepository.findAll();
    }

    public Optional<Movimiento> obtenerMovimientoPorId(Long id) {
        return movimientoRepository.findById(id);
    }

    public Movimiento registrarMovimiento(Movimiento movimiento) {
        if (movimiento.getCuenta() == null || movimiento.getCuenta().getId() == null) {
            throw new IllegalArgumentException("El movimiento debe estar asociado a una cuenta existente.");
        }

        //  Obtener la cuenta desde la base de datos
        Optional<Cuenta> cuentaOpt = cuentaRepository.findById(movimiento.getCuenta().getId());
        if (cuentaOpt.isEmpty()) {
            throw new IllegalArgumentException("No se puede registrar el movimiento. La cuenta con ID " + movimiento.getCuenta().getId() + " no existe.");
        }

        Cuenta cuenta = cuentaOpt.get();
        double saldoAnterior = cuenta.getSaldoInicial(); // 🔹 Guardamos el saldo antes del movimiento
        double nuevoSaldo = saldoAnterior + movimiento.getValor(); // 🔹 Calculamos el nuevo saldo

        //  Validar saldo insuficiente antes de actualizar
        if (movimiento.getValor() < 0 && saldoAnterior < Math.abs(movimiento.getValor())) {
            throw new IllegalArgumentException("Saldo no disponible.");
        }

        //  Actualizar saldo en la cuenta y guardar
        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        //  Asignar saldo anterior y nuevo saldo al movimiento
        movimiento.setSaldoAnterior(saldoAnterior);
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setCuenta(cuenta);

        return movimientoRepository.save(movimiento);
    }


}
