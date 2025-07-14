package com.gbr.product_manager.api.mapper;

import com.gbr.product_manager.api.dto.ProductRequestDTO;
import com.gbr.product_manager.api.dto.ProductResponseDTO;
import com.gbr.product_manager.infrastructure.entity.Product;

public class ProductMapper {

    public static ProductResponseDTO toResponseDTO(Product product){
        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static Product toEntity(ProductRequestDTO dto){
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();
    }


}
