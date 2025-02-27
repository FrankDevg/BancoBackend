package com.banco.clientes.application.service;

import com.banco.clientes.application.exception.ClienteNotFoundException;
import com.banco.clientes.application.exception.CuentasNoEncontradasException;

import com.banco.clientes.domain.entities.Cliente;
import com.banco.clientes.domain.entities.ClienteCuenta;
import com.banco.clientes.domain.repository.ClienteRepository;
import com.banco.clientes.domain.repository.ClienteCuentaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteCuentaRepository clienteCuentaRepository;
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteCuentaRepository clienteCuentaRepository,
                          ClienteRepository clienteRepository) {
        this.clienteCuentaRepository = clienteCuentaRepository;
        this.clienteRepository = clienteRepository;
    }

    // 🔹 Obtener todos los clientes
    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    // 🔹 Obtener cliente por ID con excepción si no existe
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente con ID " + id + " no encontrado"));
    }

    // 🔹 Guardar cliente
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // 🔹 Actualizar cliente
    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente con ID " + id + " no encontrado"));

        if (clienteActualizado == null) {
            throw new IllegalArgumentException("El objeto clienteActualizado no puede ser null");
        }

        cliente.setNombre(clienteActualizado.getNombre());
        cliente.setGenero(clienteActualizado.getGenero());
        cliente.setEdad(clienteActualizado.getEdad());
        cliente.setIdentificacion(clienteActualizado.getIdentificacion());
        cliente.setDireccion(clienteActualizado.getDireccion());
        cliente.setTelefono(clienteActualizado.getTelefono());
        cliente.setContrasenia(clienteActualizado.getContrasenia());
        cliente.setEstado(clienteActualizado.getEstado());

        return clienteRepository.save(cliente);
    }

    // 🔹 Verificar si un cliente existe
    public boolean existeCliente(Long id) {
        return clienteRepository.existsById(id);
    }

    // 🔹 Eliminar cliente con verificación
    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNotFoundException("Cliente con ID " + id + " no encontrado");
        }
        clienteRepository.deleteById(id);
    }

    // 🔹 Obtener cuentas asociadas a un cliente
    public List<Long> obtenerCuentasPorCliente(Long clienteId) {
        List<Long> cuentas = clienteCuentaRepository.findByClienteId(clienteId)
                .stream()
                .map(ClienteCuenta::getCuentaId)
                .collect(Collectors.toList());

        if (cuentas.isEmpty()) {
            throw new CuentasNoEncontradasException("No se encontraron cuentas asociadas al cliente con ID " + clienteId);
        }

        return cuentas;
    }

    // 🔹 Asignar cuenta a cliente (verifica si ya está asignada)
    public ClienteCuenta asignarCuentaACliente(Long clienteId, Long cuentaId) {
        if (clienteCuentaRepository.existsByCuentaId(cuentaId)) {
            throw new ClienteNotFoundException("La cuenta con ID " + cuentaId + " ya está asignada a otro cliente.");
        }

        ClienteCuenta clienteCuenta = new ClienteCuenta(clienteId, cuentaId);
        return clienteCuentaRepository.save(clienteCuenta);
    }

    // 🔹 Eliminar cuenta de un cliente
    public void eliminarCuentaDeCliente(Long clienteId, Long cuentaId) {
        ClienteCuenta clienteCuenta = clienteCuentaRepository.findByClienteIdAndCuentaId(clienteId, cuentaId);
        if (clienteCuenta == null) {
            throw new ClienteNotFoundException("No se encontró la cuenta asociada al cliente con ID " + clienteId);
        }
        clienteCuentaRepository.delete(clienteCuenta);
    }
}
