package com.springboot.app.item.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.app.commons.models.Product;


@FeignClient(name = "service-products")
public interface ProductClientRest {

	@GetMapping("/list")
	public List<Product> findAll();
	
	@GetMapping("/find/{id}")
	public Product detail(@PathVariable Long id);
	
	@PostMapping("/save")
	public Product save(@RequestBody Product product);
	
	@PutMapping("/modify/{id}")
	public Product modify(@RequestBody Product product, @PathVariable Long id);
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id);
}
