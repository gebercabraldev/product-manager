package com.gbr.product_manager.api.controller;

import com.gbr.product_manager.business.ProductService;
import com.gbr.product_manager.infrastructure.entity.Product;
import com.gbr.product_manager.api.mapper.ProductMapper;
import com.gbr.product_manager.api.dto.ProductRequestDTO;
import com.gbr.product_manager.api.dto.ProductResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getById(@PathVariable Long id){
        Product product = service.getById(id);
        return ResponseEntity.ok(ProductMapper.toResponseDTO(product));
    }

    @GetMapping
    public List<ProductResponseDTO>getAll(){
        return service.getAll().stream()
                .map(ProductMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> save(@Valid @RequestBody ProductRequestDTO dto){
        Product saved = service.save(ProductMapper.toEntity(dto));
        return ResponseEntity.ok(ProductMapper.toResponseDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO>update(@PathVariable Long id,
                                                    @Valid @RequestBody ProductRequestDTO dto){
        Product product = ProductMapper.toEntity(dto);
        product.setId(id);
        Product updated = service.update(product);
        return ResponseEntity.ok(ProductMapper.toResponseDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();

    }

}
