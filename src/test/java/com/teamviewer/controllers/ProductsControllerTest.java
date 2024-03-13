package com.teamviewer.controllers;

import com.teamviewer.TestDataUtil;
import com.teamviewer.repositories.ProductsRepository;
import com.teamviewer.service.ProductsService;
import com.teamviewer.models.ProductModel;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductsControllerTest {

    @Autowired
    private ProductsController productsController;

    @Autowired
    @InjectMocks
    private ProductsService productsService;

    @Mock
    private ProductsRepository productsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getProducts() {
        List<ProductModel> mockProducts = TestDataUtil.mockProducts(10);
        when(productsRepository.findAll()).thenReturn(mockProducts);

        List<ProductModel> products = productsController.getProducts();
        Assert.notNull(products, "Products are empty");
        Assert.notEmpty(products, "Products are empty");
    }


    @Test
    void getProduct() {
        // Can't get product that does not exist
        assertThrows(EntityNotFoundException.class, () -> {
            productsController.getProduct(9999L);
        });

        List<ProductModel> mockProducts = TestDataUtil.mockProducts(1);
        ProductModel mockProduct = mockProducts.get(0);
        when(productsRepository.findById(1L)).thenReturn(Optional.ofNullable(mockProduct));

        // Get product
        ProductModel product = productsController.getProduct(1);
        Assert.notNull(product, "Product is empty");
        Assert.isTrue(mockProduct.equals(product), "Product does not match");
    }

    @Test
    void createProduct() {
        List<ProductModel> mockProducts = TestDataUtil.mockProducts(1);
        ProductModel mockProduct = mockProducts.get(0);
        when(productsRepository.save(mockProduct)).thenReturn(mockProduct);

        productsController.createProduct(mockProduct);
    }

    @Test
    void updateProduct() {
        List<ProductModel> mockProducts = TestDataUtil.mockProducts(1);
        ProductModel mockProduct = mockProducts.get(0);
        when(productsRepository.findById(1L)).thenReturn(Optional.ofNullable(mockProduct));
        when(productsRepository.findById(9999L)).thenReturn(Optional.empty());
        when(productsRepository.save(mockProduct)).thenReturn(mockProduct);

        // Can't update product that does not exist
        assertThrows(EntityNotFoundException.class, () -> {
            productsController.updateProduct(9999L, mockProduct);
        });

        // Update ok
        ProductModel product = productsController.updateProduct(1L, mockProduct);
        Assert.notNull(product, "Product is empty");
        Assert.isTrue(mockProduct.equals(product), "Products do not match");
    }

    @Test
    void deleteProduct() {
        // Can't delete product that does not exist
        assertThrows(EntityNotFoundException.class, () -> {
            productsController.deleteProduct(9999L);
        });
    }
}