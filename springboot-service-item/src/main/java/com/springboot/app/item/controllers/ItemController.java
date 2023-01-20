package com.springboot.app.item.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.commons.models.Product;
import com.springboot.app.item.models.Item;
import com.springboot.app.item.services.ItemService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class ItemController {
	
	private final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private CircuitBreakerFactory cbFactory;
	
	@Autowired
	@Qualifier("itemServiceFeign")
	private ItemService itemService;
	
	@GetMapping("/list")
	public List<Item> findAll(@RequestParam(name="name") String name, @RequestHeader(name="token-request") String token){
		//System.out.println("name: " + name + " token: " + token );
		return itemService.findAll();
	}
	
	@GetMapping("/find/{id}/amount/{amount}")
	public Item detail(@PathVariable Long id, @PathVariable Integer amount){
		return cbFactory.create("items").run(() -> itemService.findById(id, amount), e -> alternativeMethod(id, amount, e));
	}
	
	@CircuitBreaker(name="items", fallbackMethod = "alternativeMethod")
	@GetMapping("/find2/{id}/amount/{amount}")
	public Item detail2(@PathVariable Long id, @PathVariable Integer amount){
		return itemService.findById(id, amount);
	}
	
	public Item alternativeMethod(Long id, Integer amount, Throwable e) {
		logger.info(e.getMessage());
		return new Item(new Product(id, "Camera Sony", 500.00), amount);
	}
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Product save(@RequestBody Product product) {
		return itemService.save(product);
	}
	
	@PutMapping("/modify/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product modify(@RequestBody Product product, @PathVariable Long id) {
		return itemService.modify(product, id);
	}	
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		itemService.delete(id);
	}

}
