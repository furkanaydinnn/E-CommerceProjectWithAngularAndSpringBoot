package com.ecommerce.springbootecommerce.controller;

import java.util.Optional;

import com.ecommerce.springbootecommerce.entity.ProductCategory;
import com.ecommerce.springbootecommerce.service.ProductCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/product-category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("")
    public ResponseEntity<Optional<ProductCategory>> getProductCategory(@RequestParam Long id) {

        try {
            Optional<ProductCategory> productCategory = productCategoryService.getProductCategoryById(id);
            return productCategory.isPresent() ? ResponseEntity.ok().body(productCategory)
                    : ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }

    }

}
