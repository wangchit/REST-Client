package com.abc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.abc.rest.client.CatalogClient;
import com.abc.rest.client.ProductClient;
import com.abc.rest.model.Catalog;
import com.abc.rest.model.CatalogList;
import com.abc.rest.model.Product;

public class EStoreClientApp {

    public static void main(String[] args) throws Exception {
    	EStoreClientApp app = new EStoreClientApp();
        app.run();
    }
    
    public void run() {
    	ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("/META-INF/spring/EStoreClientContext.xml");
        CatalogClient client = (CatalogClient) context.getBean("catalogClient");
        ProductClient productClient = (ProductClient) context.getBean("productClient");

        // Get catalogs
    	System.out.println("\n==== Get Catalogs ...");
    	ArrayList<Catalog> catalogs = client.getCatalogs();
    	System.out.println("---- Total catalogs=" + catalogs.size());
    	for (int i=0; i<catalogs.size(); i++) {
    	    System.out.println("ctlg[" + i + "]=" + catalogs.get(i));	
    	}

    	// Get catalogs by id
    	System.out.println("\n==== Get Catalogs by Id ...");
       	System.out.println("----   [id=" + catalogs.get(0).getCatalogId() + "] " 
       			+ client.getCatalog(catalogs.get(0).getCatalogId()));
       	System.out.println("----   [id=" + catalogs.get(1).getCatalogId() + "] " 
       			+ client.getCatalog(catalogs.get(1).getCatalogId()));

       	// Add a new catalog
    	System.out.println("\n==== Add new Catalog ...");
    	Catalog ctlg = new Catalog();
    	ctlg.setCatalogName("JavaScript Book");
    	client.createCatalog(ctlg);

    	catalogs = client.getCatalogs();
    	for (int i=0; i<catalogs.size(); i++) {
    	    System.out.println("ctlg[" + i + "]=" + catalogs.get(i));
    	    if ("JavaScript Book".equals(catalogs.get(i).getCatalogName()))
    	    	ctlg = catalogs.get(i);
    	}
       	
       	// Update a catalog
    	System.out.println("\n==== Update a Catalog ...");
    	
    	// Unable to delete existing Catalog because of FOREIGN KEY CONSTRAINT. Error in "Console".
    	// Only able delete newly created Catalog "JavaScript Book"
    	ctlg = catalogs.get(catalogs.size()-2);
    	ctlg.setCatalogName(ctlg.getCatalogName() + "-2");
    	client.updateCatalog(ctlg);

    	catalogs = client.getCatalogs();
    	for (int i=0; i<catalogs.size(); i++) {
    	    System.out.println("ctlg[" + i + "]=" + catalogs.get(i));	
    	}

       	// Delete a catalog
    	System.out.println("\n==== Delete a Catalog ...");
    	ctlg = catalogs.get(catalogs.size()-1);
    	client.deleteCatalog(ctlg);

    	catalogs = client.getCatalogs();
    	for (int i=0; i<catalogs.size(); i++) {
    	    System.out.println("ctlg[" + i + "]=" + catalogs.get(i));	
    	}
    
    
    	
    	 // Get products
        System.out.println("\n==== Get Products ...");
    		
    		ArrayList<Product> products = productClient.getProducts();
    		System.out.println("---- Total products=" + products.size());
    		for (int i=0; i<products.size(); i++) {
    			System.out.println("prdt[" + i + "]=" + products.get(i));	
    	}

    	// Get products by id
    	System.out.println("\n==== Get Products by Id ...");
       	System.out.println("----   [id=" + products.get(0).getProductId() + "] " 
       			+ productClient.getProduct(products.get(0).getProductId()));
       	System.out.println("----   [id=" + products.get(1).getProductId() + "] " 
       			+ productClient.getProduct(products.get(1).getProductId()));

       	// Add a new product
    	System.out.println("\n==== Add new Product ...");
    	Product prdt = new Product();
    	prdt.setName("Java Enterprise");
    	prdt.setSku(18);
    	prdt.setAvailableQuantity(10);
    	prdt.setPrice(20.5F);
    	prdt.setUnitOfMeasure("e10");
    	prdt.setCatalog(catalogs.get(0));
    	
    	productClient.createProduct(prdt);

    	products = productClient.getProducts();
    	for (int i=0; i<products.size(); i++) {
    	    System.out.println("prdt[" + i + "]=" + products.get(i));
    	    if ("Java Enterprise".equals(products.get(i).getName()))
    	    	prdt = products.get(i);
    	}
       	
       	// Update a product
    	System.out.println("\n==== Update a Product ...");
    	prdt = products.get(products.size()-1);
    	prdt.setName(prdt.getName() + "-2");
    	productClient.updateProduct(prdt);

    	products = productClient.getProducts();
    	for (int i=0; i<products.size(); i++) {
    	    System.out.println("prdt[" + i + "]=" + products.get(i));	
    	}

       	// Delete a product
    	System.out.println("\n==== Delete a Product ...");
    	prdt = products.get(products.size()-1);
    	productClient.deleteProduct(prdt);

    	products = productClient.getProducts();
    	for (int i=0; i<products.size(); i++) {
    	    System.out.println("prdt[" + i + "]=" + products.get(i));	
    	}
    }    
    
}
