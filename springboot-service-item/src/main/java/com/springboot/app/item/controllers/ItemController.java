package com.springboot.app.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.item.models.Item;
import com.springboot.app.item.services.ItemService;

@RestController
public class ItemController {

	@Autowired
	@Qualifier("itemServiceFeign")
	private ItemService itemService;
	
	@GetMapping("/items")
	public List<Item> findAll(){
		return itemService.findAll();
	}
	
	@GetMapping("/items/{id}/amount/{amount}")
	public Item detail(@PathVariable Long id, @PathVariable Integer amount){
		return itemService.findById(id, amount);
	}

}
