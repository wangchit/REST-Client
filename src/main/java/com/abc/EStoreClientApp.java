package com.abc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.abc.rest.client.CatalogClient;
import com.abc.rest.model.Catalog;
import com.abc.rest.model.CatalogList;

public class EStoreClientApp {

    public static void main(String[] args) throws Exception {
    	EStoreClientApp app = new EStoreClientApp();
        app.run();
    }
    
    public void run() {
    	ClassPathXmlApplicationContext context = 
                new ClassPathXmlApplicationContext("/META-INF/spring/EStoreClientContext.xml");
        CatalogClient client = (CatalogClient) context.getBean("catalogClient");

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
    	ctlg.setCatalogName("Finance");
    	client.createCatalog(ctlg);

    	catalogs = client.getCatalogs();
    	for (int i=0; i<catalogs.size(); i++) {
    	    System.out.println("ctlg[" + i + "]=" + catalogs.get(i));
    	    if ("Finance".equals(catalogs.get(i).getCatalogName()))
    	    	ctlg = catalogs.get(i);
    	}
       	
       	// Update a catalog
    	System.out.println("\n==== Update a Catalog ...");
//    	ctlg = catalogs.get(catalogs.size()-1);
    	ctlg.setCatalogName(ctlg.getCatalogName() + "-2");
    	client.updateCatalog(ctlg);

    	catalogs = client.getCatalogs();
    	for (int i=0; i<catalogs.size(); i++) {
    	    System.out.println("ctlg[" + i + "]=" + catalogs.get(i));	
    	}

       	// Delete a catalog
    	System.out.println("\n==== Delete a Catalog ...");
//    	ctlg = catalogs.get(catalogs.size()-1);
    	client.deleteCatalog(ctlg);

    	catalogs = client.getCatalogs();
    	for (int i=0; i<catalogs.size(); i++) {
    	    System.out.println("ctlg[" + i + "]=" + catalogs.get(i));	
    	}
    }
}
