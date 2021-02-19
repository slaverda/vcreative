package com.vcreaative.controller;

import com.sands.vcreative.model.ProductFull;
import com.vcreative.core.api.controller.ProductController;
import com.vcreative.core.api.model.exceptions.NotFoundException;
import com.vcreative.core.api.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @Spy
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductServiceImpl productService;

    @Test
    @DisplayName("Should return successful response on calling get product by id endpoint")
    public void productById() {
        var productId = "some-id";
        var productResponse = new ProductFull();
        productResponse.setId(productId);

        when(productService.getProductById(productId)).thenReturn(Optional.of(productResponse));

        var response = productController.getProductByIdUsingGET(productId);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getId(), is(productId));
    }

    @Test
    @DisplayName("Should return not found response when product not found")
    public void productByIdNotFound() {
        var productId = "some-id";

        when(productService.getProductById(productId)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () ->
                productController.getProductByIdUsingGET(productId));
    }

    @Test
    @DisplayName("Should throw InvalidParameterException when blank id is provided")
    public void productByIdInvalidParameter() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                productController.getProductByIdUsingGET(" "));
    }

    @Test
    @DisplayName("Should throw InvalidParameterException if blank name is provided")
    public void productByNameInvalidParameter() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                productController.getProductByNameUsingGET(" ", 0, 20));
    }
}
