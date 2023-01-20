package com.springboot.app.products.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.app.commons.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
}
