package com.banco.cuentas.application.service;

import com.banco.cuentas.application.exception.MovimientoNotFoundException;
import com.banco.cuentas.application.exception.CuentaNotFoundException;
import com.banco.cuentas.application.exception.SaldoInsuficienteException;
import com.banco.cuentas.domain.entities.Movimiento;
import com.banco.cuentas.domain.entities.Cuenta;
import com.banco.cuentas.domain.repository.MovimientoRepository;
import com.banco.cuentas.domain.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoService {
    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    public MovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    // 🔹 Obtener todos los movimientos
    public List<Movimiento> obtenerMovimientos() {
        return movimientoRepository.findAll();
    }

    // 🔹 Obtener un movimiento por ID con excepción si no existe
    public Movimiento obtenerMovimientoPorId(Long id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new MovimientoNotFoundException("Movimiento con ID " + id + " no encontrado"));
    }

    // 🔹 Registrar un nuevo movimiento con validaciones
    public Movimiento registrarMovimiento(Movimiento movimiento) {
        if (movimiento.getCuenta() == null || movimiento.getCuenta().getId() == null) {
            throw new IllegalArgumentException("El movimiento debe estar asociado a una cuenta existente.");
        }

        // 🔹 Verificar si la cuenta existe
        Cuenta cuenta = cuentaRepository.findById(movimiento.getCuenta().getId())
                .orElseThrow(() -> new CuentaNotFoundException("No se puede registrar el movimiento. La cuenta con ID " + movimiento.getCuenta().getId() + " no existe."));

        double saldoAnterior = cuenta.getSaldoInicial();
        double nuevoSaldo = saldoAnterior + movimiento.getValor(); // 🔹 Se suma para ingresos y resta para retiros

        // 🔹 Validar saldo insuficiente antes de actualizar
        if (movimiento.getValor() < 0 && saldoAnterior < Math.abs(movimiento.getValor())) {
            throw new SaldoInsuficienteException("Saldo no disponible para la transacción.");
        }

        // 🔹 Actualizar saldo en la cuenta y guardar
        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepository.save(cuenta);

        // 🔹 Asignar saldo anterior y nuevo saldo al movimiento
        movimiento.setSaldoAnterior(saldoAnterior);
        movimiento.setSaldo(nuevoSaldo);
        movimiento.setCuenta(cuenta);

        return movimientoRepository.save(movimiento);
    }
}
