package com.springboot.app.products.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
		return productService.findById(id);
	}
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Product save(@RequestBody Product product){
		return productService.save(product);
	}
	
	@PutMapping("/modify/{id}")
	public Product save(@RequestBody Product product, @PathVariable Long id) throws Exception{
		Product productDB = productService.findById(id);
		productDB.setName(product.getName());
		productDB.setPrice(product.getPrice());
		return productService.save(productDB);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		productService.deleteById(id);
	}
	
}
