package com.gbr.product_manager.infrastructure.repository;

import com.gbr.product_manager.infrastructure.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
