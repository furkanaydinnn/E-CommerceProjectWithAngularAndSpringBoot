package com.ecommerce.springbootecommerce.service;

import java.util.Optional;

import com.ecommerce.springbootecommerce.entity.ProductCategory;

public interface ProductCategoryService {

    Optional<ProductCategory> getProductCategoryById(Long id);

}
