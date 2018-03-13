package com.abc.rest.client;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.abc.rest.model.Product;
import com.abc.rest.model.ProductList;

@Controller
public class ProductClient {

	//RestTemplate is injected here
	private RestTemplate rest;
	
	@Autowired
	public void setRest(RestTemplate rest) {
		this.rest = rest;
	}
	
	// Get all products
	public ArrayList<Product> getProducts() {
		System.out.println("******** CLIENT: getProducts");
		
		ProductList productList = rest.getForObject( 
		    "http://localhost:8080/rest/products", ProductList.class);
		return productList.getProducts();
	}	
	
	// Get a product by id
	public Product getProduct(int prdtId) {
		System.out.println("******** CLIENT: getProduct");
		
		Product product = rest.getForObject( 
		    "http://localhost:8080/rest/products/{id} ", 
		    Product.class, prdtId);
		return product;
	}	
	
	// Create a new product
	public void createProduct(Product prdt) {
		System.out.println("******** CLIENT: createProduct");
		
		ResponseEntity response = rest.postForEntity(
		    "http://localhost:8080/rest/products", prdt, null);
		
		if (response.getStatusCode() != HttpStatus.CREATED) {
			throw new RuntimeException("Error creating a product)");
		}
		
	}	
	
	// Update a new product
	public void updateProduct(Product product) {
		System.out.println("******** CLIENT: updateProduct");
		
		rest.put("http://localhost:8080/rest/products/{prdtId}", 
				product, product.getProductId());
	}	
	
	// Delete a product
	public void deleteProduct(Product prdt) {
		System.out.println("******** CLIENT: deleteProduct");
		
		rest.delete(
		    "http://localhost:8080/rest/products/{prdtId}", 
		    prdt.getProductId());
	}	
	
}
