package com.springboot.app.item.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.app.commons.models.Product;
import com.springboot.app.item.models.Item;

@Service("ItemServiceImpl")
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private RestTemplate clientRest;

	@Override
	public List<Item> findAll() {
		List<Product> products = Arrays.asList(clientRest.getForObject("http://localhost:8090/api/products/list", Product[].class));
		return products.stream().map(p -> new Item(p,1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer amount) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Product product = clientRest.getForObject("http://localhost:8090/api/products/find/{id}", Product.class, pathVariables);
		return new Item(product, amount);
	}

	@Override
	public Product save(Product product) {
		HttpEntity<Product> body = new HttpEntity<Product>(product);
		return clientRest.exchange("http://localhost:8090/api/products/save", HttpMethod.POST, body, Product.class).getBody();
	}

	@Override
	public Product modify(Product product, Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		HttpEntity<Product> body = new HttpEntity<Product>(product);
		return clientRest.exchange("http://localhost:8090/api/products/modify/{id}", HttpMethod.PUT, body, Product.class, pathVariables).getBody();
	}

	@Override
	public void delete(Long id) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		clientRest.delete("http://localhost:8090/api/products/delete/{id}", pathVariables);
	}

}
