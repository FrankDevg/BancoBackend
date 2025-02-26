package com.banco.clientes.domain.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void testClienteConstructorAndGetters() {
        // 🔹 Crear cliente de prueba
        Cliente cliente = new Cliente(1L, "Juan Pérez", "M", 30, "12345678", "Calle 123", "0987654321", "pass123", true);

        // 🔹 Verificar que los valores se asignaron correctamente
        assertEquals(1L, cliente.getId());
        assertEquals("Juan Pérez", cliente.getNombre());
        assertEquals("M", cliente.getGenero());
        assertEquals(30, cliente.getEdad());
        assertEquals("12345678", cliente.getIdentificacion());
        assertEquals("Calle 123", cliente.getDireccion());
        assertEquals("0987654321", cliente.getTelefono());
        assertEquals("pass123", cliente.getContrasenia());
        assertTrue(cliente.getEstado());
    }

    @Test
    void testSetters() {
        // 🔹 Crear cliente vacío
        Cliente cliente = new Cliente();

        // 🔹 Asignar valores con setters
        cliente.setId(2L);
        cliente.setNombre("María López");
        cliente.setGenero("F");
        cliente.setEdad(28);
        cliente.setIdentificacion("87654321");
        cliente.setDireccion("Avenida 456");
        cliente.setTelefono("1234567890");
        cliente.setContrasenia("mypass456");
        cliente.setEstado(false);

        // 🔹 Verificar que los valores se actualizaron correctamente
        assertEquals(2L, cliente.getId());
        assertEquals("María López", cliente.getNombre());
        assertEquals("F", cliente.getGenero());
        assertEquals(28, cliente.getEdad());
        assertEquals("87654321", cliente.getIdentificacion());
        assertEquals("Avenida 456", cliente.getDireccion());
        assertEquals("1234567890", cliente.getTelefono());
        assertEquals("mypass456", cliente.getContrasenia());
        assertFalse(cliente.getEstado());
    }
}
