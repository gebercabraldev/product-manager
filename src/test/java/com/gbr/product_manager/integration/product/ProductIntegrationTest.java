package com.gbr.product_manager.integration.product;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.gbr.product_manager.api.dto.ProductRequestDTO;

import com.gbr.product_manager.infrastructure.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class ProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should create a product and retrieve it successfully")
    void shouldCreateAndGetProduct() throws Exception {
        ProductRequestDTO dto = ProductRequestDTO.builder()
                .name("Mouse")
                .description("Mouse gamer")
                .price(BigDecimal.valueOf(200.00))
                .build();

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mouse"));


        mockMvc.perform(get("/api/products"))
                        .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Mouse"));
    }

    @Test
    @DisplayName("Should return 400 when trying to create a product without a name")
    void shouldReturn400WhenCreatingProductWithMissingName() throws Exception {
        ProductRequestDTO dto = ProductRequestDTO.builder()
                .name("")
                .description("No name")
                .price(BigDecimal.valueOf(100.00))
                .build();

        mockMvc.perform(post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should delete a product and confirm it's no longer accessible")
    void shouldDeleteProductSuccessfully() throws Exception {
        ProductRequestDTO dto = ProductRequestDTO.builder()
                .name("Keyboard")
                .description("Mechanical keyboard")
                .price(BigDecimal.valueOf(300.00))
                .build();

        MvcResult result = mockMvc.perform(post("/api/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        Long id = objectMapper.readTree(responseJson).get("id").asLong();

        mockMvc.perform(delete("/api/products/"+id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/products/"+id))
                .andExpect(status().isNotFound());

    }


}
