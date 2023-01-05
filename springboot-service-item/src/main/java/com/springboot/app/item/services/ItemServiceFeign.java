package com.springboot.app.item.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.springboot.app.item.clients.ProductClientRest;
import com.springboot.app.item.models.Item;

@Service("itemServiceFeign")
@Primary
public class ItemServiceFeign implements ItemService {
	
	@Autowired
	private ProductClientRest client;

	@Override
	public List<Item> findAll() {
		return client.findAll().stream().map(p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer amount) {
		return new Item(client.detail(id), amount);
	}

}
