package com.springboot.app.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.item.models.Item;
import com.springboot.app.item.services.ItemService;

@RestController
public class ItemController {

	@Autowired
	@Qualifier("itemServiceFeign")
	private ItemService itemService;
	
	@GetMapping("/list")
	public List<Item> findAll(@RequestParam(name="name") String name, @RequestHeader(name="token-request") String token){
		System.out.println("name: " + name + " token: " + token );
		return itemService.findAll();
	}
	
	@GetMapping("/find/{id}/amount/{amount}")
	public Item detail(@PathVariable Long id, @PathVariable Integer amount){
		return itemService.findById(id, amount);
	}

}
