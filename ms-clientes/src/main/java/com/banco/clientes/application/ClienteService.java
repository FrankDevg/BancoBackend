package com.banco.clientes.application;
import com.banco.clientes.domain.entities.Cliente;
import com.banco.clientes.domain.entities.ClienteCuenta;
import com.banco.clientes.domain.repository.ClienteRepository;
import com.banco.clientes.domain.repository.ClienteCuentaRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteCuentaRepository clienteCuentaRepository;
    private final ClienteRepository clienteRepository;
    private final RabbitTemplate rabbitTemplate;

    //
    public ClienteService(ClienteCuentaRepository clienteCuentaRepository,
                          ClienteRepository clienteRepository,
                          RabbitTemplate rabbitTemplate
                          ) {
        this.clienteCuentaRepository = clienteCuentaRepository;
        this.clienteRepository = clienteRepository;
        this.rabbitTemplate = rabbitTemplate;

    }


    // 🔹 Métodos CRUD para Clientes
    public List<Cliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);

        if (clienteExistente.isEmpty()) {
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }

        Cliente cliente = clienteExistente.get();

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

    public boolean existeCliente(Long id) {
        return clienteRepository.existsById(id);
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public List<Long> obtenerCuentasPorCliente(Long clienteId) {
        return clienteCuentaRepository.findByClienteId(clienteId)
                .stream()
                .map(ClienteCuenta::getCuentaId)
                .collect(Collectors.toList());
    }

    // ✅ Asignar cuenta a cliente (evita duplicados)
    public ClienteCuenta asignarCuentaACliente(Long clienteId, Long cuentaId) {
        if (clienteCuentaRepository.existsByCuentaId( cuentaId)) {
            throw new RuntimeException("Esta cuenta ya está asignada a este cliente.");
        }

        ClienteCuenta clienteCuenta = new ClienteCuenta(clienteId, cuentaId);
        return clienteCuentaRepository.save(clienteCuenta);
    }

    // ✅ Eliminar cuenta de un cliente
    public void eliminarCuentaDeCliente(Long clienteId, Long cuentaId) {
        ClienteCuenta clienteCuenta = clienteCuentaRepository.findByClienteIdAndCuentaId(clienteId, cuentaId);
        if (clienteCuenta != null) {
            clienteCuentaRepository.delete(clienteCuenta);
        } else {
            throw new RuntimeException("No se encontró la cuenta asociada al cliente.");
        }
    }
}
