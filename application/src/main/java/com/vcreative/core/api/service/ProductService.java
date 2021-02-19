package com.vcreative.core.api.service;

import com.sands.vcreative.model.ProductFull;
import com.sands.vcreative.model.ProductPaginatedList;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    Optional<ProductFull> getProductById(final String id);

    ProductPaginatedList getProductByName(final String name, final Pageable pageable);
}
