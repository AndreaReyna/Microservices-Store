package com.springboot.app.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.products.models.entity.Product;
import com.springboot.app.products.service.IproductService;

@RestController
public class ProductController {

	@Autowired
	private IproductService productService;
	
	@GetMapping("/products")
	public List<Product> findAll(){
		return productService.findAll();
	}
	
	@GetMapping("/products/{id}")
	public Product findById(@PathVariable Long id) throws Exception{
		return productService.findById(id);
	}
}
