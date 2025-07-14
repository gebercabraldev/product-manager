package com.gbr.product_manager.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductRequestDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;
    private String description;
    @NotNull(message = "Price is mandatory")
    @DecimalMin(value="0.0", inclusive = false, message = "Price must be greather than zero")
    private BigDecimal price;
}
