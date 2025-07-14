package com.gbr.product_manager.api.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Builder
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
}
