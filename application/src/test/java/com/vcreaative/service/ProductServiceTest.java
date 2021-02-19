package com.vcreaative.service;

import com.sands.vcreative.model.ProductFull;
import com.sands.vcreative.model.ProductPaginatedList;
import com.vcreative.core.api.model.Product;
import com.vcreative.core.api.model.dto.SimplePageRequest;
import com.vcreative.core.api.repository.ProductRepository;
import com.vcreative.core.api.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
//    @InjectMocks
//    private ProductServiceImpl productService;
//
//    @Mock
//    private ProductRepository productRepository;
//
//    @Mock
//    private ModelMapper modelMapper;
//
//    @Test
//    @DisplayName("should return successful response for get product by uid")
//    public void getProductByUid() {
//        String productId = "abc";
//        Product product = Product.builder().id(productId).build();
//        ProductFull breederResponse = new ProductFull().id(productId);
//
//        when(productRepository.findProductById(productId))
//                .thenReturn(Optional.of(product));
//
//        when(modelMapper.map(product, ProductFull.class)).thenReturn(breederResponse);
//
//        ProductFull actualResponse = productService.getProductById(productId).orElseThrow();
//
//        assertThat(actualResponse, is(breederResponse));
//    }
//
//    @Test
//    @DisplayName("Should return empty response when product is not found for a product uid")
//    public void productNotFound() {
//        String productUid = "abc";
//        when(productRepository.findProductById(productUid)).thenReturn(Optional.empty());
//
//        Optional<ProductFull> response = productService.getProductById(productUid);
//
//        assertTrue(response.isEmpty());
//    }
//
//    @Test
//    @DisplayName("Should return successful response for get product by name")
//    public void getProductByName() {
//        String breederName = "some name";
//        Pageable pageable = SimplePageRequest.firstPage();
//
//        Product product = Product.builder().name(breederName.toUpperCase(Locale.ROOT)).build();
//        List<Product> productList = List.of(product);
//
//        Page<Product> pagedBreeder = new PageImpl<>(productList);
//
//        ProductFull productFull = new ProductFull();
//        List<ProductFull> productFull1List = List.of(productFull);
//        Page<ProductFull> pagedProductResponse = new PageImpl<>(productFull1List);
//
//
//        when(productRepository.findProductByNameContaining(breederName.toUpperCase(Locale.ROOT), pageable))
//                .thenReturn(pagedBreeder);
//        when(modelMapper.map(product, ProductFull.class)).thenReturn(productFull);
//
//        ProductPaginatedList response = productService.getProductByName(breederName, pageable);
//
//        assertThat(response.getResults(), is(pagedProductResponse.getContent()));
//    }
//
//    @Test
//    @DisplayName("Should return empty response when product is not found for a product name")
//    public void breederNotFoundByName() {
//        String productName = "some name";
//        Pageable pageable = Pageable.unpaged();
//
//        when(productRepository.findProductByNameContaining(productName.toUpperCase(Locale.ROOT), pageable))
//                .thenReturn(new PageImpl<>(List.of()));
//        ProductPaginatedList response = productService.getProductByName(productName, pageable);
//        assertTrue(response.getResults().isEmpty());
//    }
}
