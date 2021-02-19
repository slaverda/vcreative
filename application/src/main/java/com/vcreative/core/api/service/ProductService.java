package com.vcreative.core.api.service;

import com.sands.vcreative.model.ProductFull;
import com.sands.vcreative.model.ProductPaginatedList;
import com.vcreative.core.api.model.dto.SimplePageRequest;

import java.util.Optional;

public interface ProductService {
    Optional<ProductFull> getProductById(final String id);

    ProductPaginatedList getAllProducts(final SimplePageRequest simplePageRequest);
}
