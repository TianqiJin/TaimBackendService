package com.taim.taimbackendservice.repository;

import com.taim.taimbackendservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductBySku(String sku);
}
