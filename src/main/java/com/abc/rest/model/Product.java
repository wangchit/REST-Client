package com.abc.rest.model;

import java.util.Date;


import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

	@XmlElement
	private int productId;
	@XmlElement
	private int sku;
	@XmlElement
	private String name;
	@XmlElement
	private String unitOfMeasure;
	@XmlElement
	private Float price;
	@XmlElement
	private Integer availableQuantity;
	@XmlElement
	private Catalog catalog;

	public Product() {
	}

	public Product(int productId, int sku, String name, String unitOfMeasure, Float price,
		int availableQuantity, Catalog catalog,  Date createDate) {
		
		this.productId = productId;
		this.sku = sku;
		this.name = name;
		this.unitOfMeasure = unitOfMeasure;
		this.price = price;
		this.availableQuantity = availableQuantity;
		this.catalog = catalog;
	}

	public int getProductId() {
        return productId;
    }
	
    public void setProductId(int value) {
        this.productId = value;
    }
	
	public int getSku() {
		return sku;
	}
	public void setSku(int sku) {
		this.sku = sku;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}
	
	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getAvailableQuantity() {
		return availableQuantity;
	}
	
	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	
	public Catalog getCatalog() {
		return catalog;
	}
	
	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

    public String toString() {
        StringBuilder buff = new StringBuilder("\n[Product: ")
        .append("productId=").append(getProductId())
        .append("sku=").append(getSku())
        .append(", name=").append(getName())
        .append(", unitOfMeasure=").append(getUnitOfMeasure())
        .append(", price=").append(getPrice())
        .append(", availableQuantity=").append(getAvailableQuantity())
        .append("]")
        ;

        return buff.toString();
     }
}
