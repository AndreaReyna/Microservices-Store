package com.springboot.app.products.service;

import java.util.List;

import com.springboot.app.commons.models.Product;

public interface IproductService {
	
	public List<Product> findAll();
	public Product findById(Long id) throws Exception;
	public Product save(Product product);
	public void deleteById(Long id);
}
