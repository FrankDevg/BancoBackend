package com.banco.clientes.integration;

import com.banco.clientes.domain.entities.Cliente;
import com.banco.clientes.domain.repository.ClienteRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    private static Long clienteId;

    @Test
    @Order(1)
    @DisplayName("✅ Crear Cliente Exitosamente")
    void crearClienteExitoso() throws Exception {
        String jsonCliente = """
            {
                "nombre": "Jose Lema",
                "genero": "Masculino",
                "edad": 30,
                "identificacion": "12345678",
                "direccion": "Otavalo sn y principal",
                "telefono": "098254785",
                "contrasenia": "1234",
                "estado": true
            }
        """;

        String response = mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCliente))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        clienteId = Long.valueOf(response.substring(response.indexOf("\"id\":") + 5, response.indexOf(",", response.indexOf("\"id\":"))));
    }

    @Test
    @Order(2)
    @DisplayName("✅ Obtener Cliente por ID")
    void obtenerClientePorId() throws Exception {
        mockMvc.perform(get("/clientes/" + clienteId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Jose Lema"));
    }

    @Test
    @Order(3)
    @DisplayName("✅ Actualizar Cliente")
    void actualizarCliente() throws Exception {
        String jsonActualizado = """
            {
                "nombre": "Jose Lema",
                "genero": "Masculino",
                "edad": 35,
                "identificacion": "12345678",
                "direccion": "Nueva dirección",
                "telefono": "099999999",
                "contrasenia": "12345",
                "estado": true
            }
        """;

        mockMvc.perform(put("/clientes/" + clienteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonActualizado))
                .andExpect(status().isOk());


        mockMvc.perform(get("/clientes/" + clienteId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.edad").value(35))
                .andExpect(jsonPath("$.direccion").value("Nueva dirección"))
                .andExpect(jsonPath("$.telefono").value("099999999"));
    }

    @Test
    @Order(4)
    @DisplayName("✅ Eliminar Cliente")
    void eliminarCliente() throws Exception {
        mockMvc.perform(delete("/clientes/" + clienteId))
                .andExpect(status().isNoContent());


        mockMvc.perform(get("/clientes/" + clienteId))
                .andExpect(status().isNotFound());
    }
}
