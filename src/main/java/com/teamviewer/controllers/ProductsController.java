package com.teamviewer.controllers;

import com.teamviewer.service.ProductsService;
import com.teamviewer.models.ProductModel;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController extends AbstractBaseController {

    @Autowired
    private ProductsService productsService;

    @GetMapping("")
    @Operation(summary = "GetProducts", description = "Gets all products", tags = "Products")
    public List<ProductModel> getProducts() {
        return productsService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "GetProduct", description = "Gets a product by id", tags = "Products")
    public ProductModel getProduct(@PathVariable long id) {
        return productsService.exists(id);
    }

    @PostMapping("")
    @Operation(summary = "CreateProduct", description = "Creates a product", tags = "Products")
    public ProductModel createProduct(@RequestBody ProductModel productModel) {
        return productsService.create(productModel);
    }

    @PutMapping("/{id}")
    @Operation(summary = "UpdateProduct", description = "Updates a product", tags = "Products")
    public ProductModel updateProduct(@PathVariable long id, @RequestBody ProductModel productModel) {
        productsService.exists(id);
        productModel.setId(id);
        return productsService.update(productModel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "DeleteProduct", description = "Deletes a product by id", tags = "Products")
    public void deleteProduct(@PathVariable long id) {
        productsService.delete(id);
    }
}
