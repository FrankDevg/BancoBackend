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

    public List<Cuenta> obtenerCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta obtenerCuentaPorId(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(() -> new CuentaNotFoundException("Cuenta con ID " + id + " no encontrada"));
    }

    public Cuenta guardarCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

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
    public void eliminarCuenta(Long id) {
        if (!cuentaRepository.existsById(id)) {
            throw new CuentaNotFoundException("No se puede eliminar. La cuenta con ID " + id + " no existe.");
        }
        cuentaRepository.deleteById(id);
    }

}
