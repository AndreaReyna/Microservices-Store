package com.springboot.app.products.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.commons.models.Product;
import com.springboot.app.products.models.repository.ProductRepository;

@Service
public class ProductServiceImpl implements IproductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Product findById(Long id) throws Exception {
		return productRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));
	}

	@Override
	@Transactional()
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	@Transactional()
	public void deleteById(Long id) {
		productRepository.deleteById(id);		
	}

}
