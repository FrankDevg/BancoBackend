package com.banco.clientes.infrastructure.integration;

import com.banco.clientes.domain.entities.Cliente;
import com.banco.clientes.domain.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest // ✅ Carga toda la configuración de la app
class ClienteIntegrationTest {

    @Autowired
    private ClienteRepository clienteRepository;

    private Cliente clienteTest;

    @BeforeEach
    void setUp() {
        // 🔹 Crear un cliente de prueba antes de cada test
        clienteTest = new Cliente(null, "Carlos Díaz", "M", 35, "98765432", "Calle 789", "0998765432", "clave123", true);
        clienteTest = clienteRepository.save(clienteTest);
    }

    @Test
    void testGuardarCliente() {
        // 🔹 Verificar que el cliente se guardó correctamente
        assertNotNull(clienteTest.getId());
        assertEquals("Carlos Díaz", clienteTest.getNombre());
    }

    @Test
    void testBuscarClientePorId() {
        // 🔹 Buscar cliente en la BD
        Optional<Cliente> clienteEncontrado = clienteRepository.findById(clienteTest.getId());

        // 🔹 Verificar que el cliente existe y los datos coinciden
        assertTrue(clienteEncontrado.isPresent());
        assertEquals("Carlos Díaz", clienteEncontrado.get().getNombre());
    }

    @Test
    void testActualizarCliente() {
        // 🔹 Modificar y guardar cliente
        clienteTest.setNombre("Carlos Díaz Actualizado");
        Cliente clienteActualizado = clienteRepository.save(clienteTest);

        // 🔹 Verificar cambios en la BD
        assertEquals("Carlos Díaz Actualizado", clienteActualizado.getNombre());
    }

    @Test
    void testEliminarCliente() {
        // 🔹 Eliminar cliente
        clienteRepository.delete(clienteTest);

        // 🔹 Verificar que no existe
        Optional<Cliente> clienteEliminado = clienteRepository.findById(clienteTest.getId());
        assertFalse(clienteEliminado.isPresent());
    }
}
