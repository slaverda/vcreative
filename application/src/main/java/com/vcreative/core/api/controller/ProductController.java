package com.vcreative.core.api.controller;

import com.sands.vcreative.model.ProductFull;
import com.sands.vcreative.model.ProductPaginatedList;
import com.sands.vcreative.api.ProductControllerApi;
import com.vcreative.core.api.model.exceptions.NotFoundException;
import com.vcreative.core.api.service.ProductService;
import com.vcreative.core.api.utils.PageParamsUtils;
import com.vcreative.core.api.validators.ParameterValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
public class ProductController extends BaseController implements ProductControllerApi {
    private final ProductService productService;

    @Autowired
    public ProductController(final ProductService productService) {
        this.productService = productService;
    }

    @Override
    @GetMapping(value = "/product/{id}")
    public ResponseEntity<ProductFull> getProductByIdUsingGET(final String id) {
        assertIdNotBlank(id);
        Optional<ProductFull> response = productService.getProductById(id);
        if (!response.isPresent()) {
            throw new NotFoundException();
        }
        return ResponseEntity.ok(response.get());
    }

    @Override
    @GetMapping(value = "/product")
    public ResponseEntity<ProductPaginatedList> getProductByNameUsingGET(@Valid final String name,
                                                                         @Valid final Integer pageNumber,
                                                                         @Valid final Integer pageSize) {
        ParameterValidator.validator()
                .broad(name)
                .paging(pageNumber, pageSize)
                .validateBroadParametersHavePaging("name");
        return ResponseEntity.ok(productService.getProductByName(name,
                PageParamsUtils.toPageRequest(pageNumber, pageSize)));
    }
}
