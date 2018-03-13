package com.abc.rest.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//Same implementation as the server side

@XmlRootElement(name = "catalog") //this has to match so rest & client can communicate
@XmlAccessorType(XmlAccessType.FIELD)
public class Catalog {

	@XmlElement
	private int catalogId;
	
	@XmlElement
	private String catalogName;

	public Catalog() {
	}
	
	public Catalog(int catalogId, String catalogName) {
		this.catalogId = catalogId;
		this.catalogName = catalogName;
	}
	
	public int getCatalogId() {
		return catalogId;
	}
	
	public void setCatalogId(int catalogId) {
	    this.catalogId = catalogId;
	}

	public String getCatalogName() {
		return catalogName;
	}
	
	public void setCatalogName(String catalogName) {
	    this.catalogName = catalogName;
	}
	
	public String toString() {
        StringBuilder buff = new StringBuilder("[Catalog: ")
        .append("catalogId=").append(catalogId)
        .append(", catalogName=").append(catalogName)
        .append("]")
        ;

        return buff.toString();
	}
}
