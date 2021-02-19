package com.vcreative.core.api.service.impl;

import com.sands.vcreative.model.PaginatedHeader;
import com.sands.vcreative.model.ProductFull;
import com.sands.vcreative.model.ProductPaginatedList;
import com.vcreative.core.api.model.Product;
import com.vcreative.core.api.model.dto.SimplePageRequest;
import com.vcreative.core.api.repository.ProductRepository;
import com.vcreative.core.api.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Service that handles {@code Product} related functionality
 */
@Service
@Transactional(readOnly = true)
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    /**
     * Product Service constructor that takes Product Repository and ModelMapper
     *
     * @param productRepository Repository for Product
     * @param modelMapper ModelMapper for mapping
     */
    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository, final ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Gets Product by a given Product id
     *
     * @param id Product id
     *
     * @return Optional of Product
     */
    public Optional<ProductFull> getProductById(final String id) {
        return productRepository.findProductById(id).map(product -> modelMapper
                .map(product, ProductFull.class));
    }

    public ProductPaginatedList getAllProducts(final SimplePageRequest simplePageRequest) {
        Page<Product> page = null;
        if (simplePageRequest.getPageNumber() == 0 && simplePageRequest.getPageSize() == Integer.MAX_VALUE) {
            page = productRepository.findAll(PageRequest.of(0, 10));
        } else {
            page = productRepository.findAll(PageRequest.of(simplePageRequest.getPageNumber(),
                                                            simplePageRequest.getPageSize()));
        }

        PaginatedHeader paginatedHeader = new PaginatedHeader();
        paginatedHeader.setPageNumber(page.getNumber());
        paginatedHeader.setPageSize(page.getSize());

        ProductPaginatedList productPaginatedList = new ProductPaginatedList();
        productPaginatedList.setPageRequest(paginatedHeader);
        productPaginatedList.setTotalPages(Long.valueOf(page.getTotalPages()));
        productPaginatedList.setTotalResults(page.getTotalElements());

        List<ProductFull> productFullList = new ArrayList<>();

        List<Product> productDonorList = page.getContent();

        for (Product breederDonor : productDonorList) {
            productFullList.add(Optional.of(breederDonor).map(breeder -> modelMapper
                    .map(breeder, ProductFull.class)).get());
        }

        productPaginatedList.setResults(productFullList);

        return productPaginatedList;
    }
}
