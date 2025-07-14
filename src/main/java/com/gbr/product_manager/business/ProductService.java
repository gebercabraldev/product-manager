package com.gbr.product_manager.business;

import com.gbr.product_manager.exception.ProductNotFoundException;
import com.gbr.product_manager.infrastructure.entity.Product;
import com.gbr.product_manager.infrastructure.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository repository;

    public Product getById(Long id){
        return repository.findById(id).orElseThrow(()-> new ProductNotFoundException("Product not found"));
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }

    @Transactional
    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new ProductNotFoundException("Product not found");
        }
        repository.deleteById(id);
    }

    @Transactional
    public Product update(Product product) {
        Product productEntity = repository.findById(product.getId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        applyUpdates(productEntity, product);
        return repository.saveAndFlush(productEntity);
    }


    private void applyUpdates(Product existing, Product updated){
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
    }
}
