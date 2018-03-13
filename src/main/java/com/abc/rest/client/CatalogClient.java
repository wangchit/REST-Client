package com.abc.rest.client;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.abc.rest.model.Catalog;
import com.abc.rest.model.CatalogList;

@Controller
public class CatalogClient {

	//RestTemplate is injected here
	private RestTemplate rest;
	
	@Autowired
	public void setRest(RestTemplate rest) {
		this.rest = rest;
	}
	
	// Get all catalogs
	public ArrayList<Catalog> getCatalogs() {
		System.out.println("******** CLIENT: getCatalogs");
		
		CatalogList catalogList = rest.getForObject( 
		    "http://localhost:8080/rest/catalogs", CatalogList.class);
		return catalogList.getCatalogs();
	}	
	
	// Get a catalog by id
	public Catalog getCatalog(int ctlgId) {
		System.out.println("******** CLIENT: getCatalog");
		
		Catalog catalog = rest.getForObject( 
		    "http://localhost:8080/rest/catalogs/{id} ", 
		    Catalog.class, ctlgId);
		return catalog;
	}	
	
	// Create a new catalog
	public void createCatalog(Catalog ctlg) {
		System.out.println("******** CLIENT: createCatalog");
		
		ResponseEntity response = rest.postForEntity(
		    "http://localhost:8080/rest/catalogs", ctlg, null);
		
		if (response.getStatusCode() != HttpStatus.CREATED) {
			throw new RuntimeException("Error creating a catalog)");
		}
		
	}	
	
	// Update a new catalog
	public void updateCatalog(Catalog catalog) {
		System.out.println("******** CLIENT: updateCatalog");
		
		rest.put("http://localhost:8080/rest/catalogs/{ctlgId}", 
				catalog, catalog.getCatalogId());
	}	
	
	// Delete a catalog
	public void deleteCatalog(Catalog ctlg) {
		System.out.println("******** CLIENT: deleteCatalog");
		
		rest.delete(
		    "http://localhost:8080/rest/catalogs/{ctlgId}", 
		    ctlg.getCatalogId());
	}	
	
}
