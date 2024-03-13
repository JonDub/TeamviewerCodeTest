package com.teamviewer.service;

import com.teamviewer.models.ProductModel;
import com.teamviewer.repositories.ProductsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService implements CrudService<ProductModel> {

    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<ProductModel> getAll() {
        return productsRepository.findAll();
    }

    @Override
    public Optional<ProductModel> get(long id) {
        return productsRepository.findById(id);
    }

    @Override
    public ProductModel create(ProductModel object) {
        validate(object);

        object.setId(null);
        object.setCreatedDate(new java.sql.Date(System.currentTimeMillis()));
        object.setCreatedBy("TODO");
        object.setModifiedDate(null);
        object.setModifiedBy(null);
        return productsRepository.save(object);
    }

    @Override
    public ProductModel update(ProductModel object) {
        validate(object);
        exists(object.getId());

        object.setModifiedDate(new java.sql.Date(System.currentTimeMillis()));
        object.setModifiedBy("TODO");
        return productsRepository.save(object);
    }

    @Override
    public void delete(long id) {
        ProductModel model = exists(id);
        productsRepository.delete(model);
    }

    public ProductModel exists(long id) {
        return get(id).orElseThrow(() -> new EntityNotFoundException("Product does not exist"));
    }

    /**
     * Ensure we are not inserting invalid data and specific business logic
     *
     * @param model
     */
    private void validate(ProductModel model) {
        Assert.notNull(model, "Product is empty");
    }
}
