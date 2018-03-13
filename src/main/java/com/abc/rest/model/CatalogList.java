package com.abc.rest.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "catalogList")
public class CatalogList  {
	
	ArrayList<Catalog> catalogs = new ArrayList<Catalog>(); 
	
	public void setCatalogs(ArrayList<Catalog> catalogs) {
		this.catalogs = catalogs;
	}
	
	public ArrayList<Catalog> getCatalogs() {
		return catalogs;
	}
}
