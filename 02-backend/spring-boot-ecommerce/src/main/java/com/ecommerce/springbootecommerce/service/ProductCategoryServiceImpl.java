package com.ecommerce.springbootecommerce.service;

import java.util.Optional;

import com.ecommerce.springbootecommerce.entity.ProductCategory;
import com.ecommerce.springbootecommerce.repository.ProductCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public Optional<ProductCategory> getProductCategoryById(Long id) {

        return productCategoryRepository.findById(id);
    }

}
