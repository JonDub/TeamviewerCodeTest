package com.teamviewer.repositories;

import com.teamviewer.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductModel, Long> {

}
