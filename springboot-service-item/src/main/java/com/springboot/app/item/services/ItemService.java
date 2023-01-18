package com.springboot.app.item.services;

import java.util.List;

import com.springboot.app.item.models.Item;
import com.springboot.app.item.models.Product;

public interface ItemService {

	public List<Item> findAll();
	public Item findById(Long id, Integer amount);
	
	public Product save(Product product);
	public Product modify(Product product, Long id);
	public void delete(Long id);
}
