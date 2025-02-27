package com.banco.cuentas.application.service;

import com.banco.cuentas.application.exception.CuentaNotFoundException;
import com.banco.cuentas.domain.entities.Cuenta;
import com.banco.cuentas.domain.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {
    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    // 🔹 Obtener todas las cuentas
    public List<Cuenta> obtenerCuentas() {
        return cuentaRepository.findAll();
    }

    // 🔹 Obtener cuenta por ID con excepción si no existe
    public Cuenta obtenerCuentaPorId(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new CuentaNotFoundException("Cuenta con ID " + id + " no encontrada"));
    }

    // 🔹 Guardar una nueva cuenta
    public Cuenta guardarCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    // 🔹 Actualizar cuenta existente con verificación de existencia
    public Cuenta actualizarCuenta(Long id, Cuenta cuentaActualizada) {
        if (cuentaActualizada == null) {
            throw new IllegalArgumentException("Los datos de la cuenta no pueden ser nulos.");
        }

        return cuentaRepository.findById(id).map(cuenta -> {
            cuenta.setNumeroCuenta(cuentaActualizada.getNumeroCuenta());
            cuenta.setTipoCuenta(cuentaActualizada.getTipoCuenta());
            cuenta.setSaldoInicial(cuentaActualizada.getSaldoInicial());
            cuenta.setEstado(cuentaActualizada.getEstado());
            return cuentaRepository.save(cuenta);
        }).orElseThrow(() -> new CuentaNotFoundException("Cuenta no encontrada con ID: " + id));
    }

    // 🔹 Eliminar cuenta con verificación
    public void eliminarCuenta(Long id) {
        if (!cuentaRepository.existsById(id)) {
            throw new CuentaNotFoundException("No se puede eliminar. La cuenta con ID " + id + " no existe.");
        }
        cuentaRepository.deleteById(id);
    }

}
