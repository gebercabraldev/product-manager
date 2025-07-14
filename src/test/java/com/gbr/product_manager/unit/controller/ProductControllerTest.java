package com.gbr.product_manager.unit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbr.product_manager.api.controller.ProductController;
import com.gbr.product_manager.api.dto.ProductRequestDTO;
import com.gbr.product_manager.api.dto.ProductResponseDTO;
import com.gbr.product_manager.business.ProductService;
import com.gbr.product_manager.exception.ProductNotFoundException;
import com.gbr.product_manager.infrastructure.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.math.BigDecimal;
import java.util.List;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private Product product;

    private static final String urlBase="/api/products";

    @BeforeEach
    void setUp(){
        product = Product.builder()
                .id(1L)
                .name("Notebook")
                .description("Ultrabook")
                .price(BigDecimal.valueOf(5000.00))
                .build();
    }

    @Test
    @DisplayName("Should return 400 when product data is invalid")
    void shouldReturn400WhenProductIsInvalid() throws Exception {
        String invalidJson = """
            {
                "name": "",
                "description": "Sem nome",
                "price": 100
            }
            """;

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return a list of products")
    void shouldReturnProductList()throws Exception{
        Mockito.when(productService.getAll()).thenReturn(List.of(product));

        mockMvc.perform(get(urlBase))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Notebook"));
    }

    @Test
    @DisplayName("Should create a product successfully")
    void shouldCreateProduct()throws Exception{
        Mockito.when(productService.save(any())).thenReturn(product);

        ProductRequestDTO dto =ProductRequestDTO.builder()
                .name("Notebook")
                .description("Ultrabook")
                .price( BigDecimal.valueOf(5000.00))
                .build();

        mockMvc.perform(post(urlBase)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("Should delete a product successfully")
    void shouldDeleteProduct()throws Exception{
        mockMvc.perform(delete(urlBase+ "/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Should update a product successfully")
    void shouldUpdateProduct()throws Exception{
        Mockito.when(productService.update(any())).thenReturn(product);

        ProductRequestDTO dto = ProductRequestDTO.builder()
                .name("Notebook Atualizado")
                .description("Ultrabook Atualizado")
                .price(BigDecimal.valueOf(5500.00))
                .build();

        mockMvc.perform(put(urlBase+"/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Notebook"));
    }

    @Test
    @DisplayName("Should return an empty product list")
    void shouldReturnEmptyList() throws Exception {
        Mockito.when(productService.getAll()).thenReturn(List.of());

        mockMvc.perform(get(urlBase))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DisplayName("Should return 400 when price is negative")
    void shouldReturn400WhenPriceIsNegative()throws Exception{
        ProductResponseDTO dto = ProductResponseDTO.builder()
                .name("Invalid Product")
                .description("Negative price")
                .price(BigDecimal.valueOf(-10.00))
                .build();

        mockMvc.perform(post(urlBase)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return 404 when trying to delete a non-existent product")
    void shouldReturn404WhenDeletingNonExistentProduct() throws Exception {
        Mockito.doThrow(new ProductNotFoundException("Product not found"))
                .when(productService).delete(999L);

        mockMvc.perform(delete(urlBase+"/999"))
                .andExpect(status().isNotFound());
    }
}
