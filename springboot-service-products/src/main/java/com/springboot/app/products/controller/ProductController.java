package com.springboot.app.products.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
	
	@GetMapping("/list")
	public List<Product> findAll(){
		return productService.findAll();
	}
	
	@GetMapping("/find/{id}")
	public Product findById(@PathVariable Long id) throws Exception{
		if(id.equals(3L)) {
			throw new IllegalStateException("Not found");
		}
		if(id.equals(4L)) {
			TimeUnit.SECONDS.sleep(5L);
		}
		return productService.findById(id);
	}
}
