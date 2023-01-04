package com.springboot.app.products.service;

import java.util.List;

import com.springboot.app.products.models.entity.Product;

public interface IproductService {
	
	public List<Product> findAll();
	public Product findById(Long id) throws Exception;
}
