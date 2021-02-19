package com.vcreative.core.api.repository;

import com.vcreative.core.api.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Data access object for {@code Product}
 */
public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findProductById(String id);

    Page<Product> findProductByNameContaining(String name, Pageable pageable);
}
