package com.banco.cuentas.application;

import com.banco.cuentas.domain.entities.Cuenta;
import com.banco.cuentas.domain.repository.CuentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {
    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public List<Cuenta> obtenerCuentas() {
        return cuentaRepository.findAll();
    }

    public Optional<Cuenta> obtenerCuentaPorId(Long id) {
        return cuentaRepository.findById(id);
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
        }).orElseThrow(() -> new IllegalArgumentException("Cuenta no encontrada con ID: " + id));
    }

    public void eliminarCuenta(Long id) {
        if (!cuentaRepository.existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar. La cuenta con ID " + id + " no existe.");
        }
        cuentaRepository.deleteById(id);
    }
}
