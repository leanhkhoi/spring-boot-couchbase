package com.example.couchbase.product;


import com.example.couchbase.AbstractCriteria;

public class ProductCriteria extends AbstractCriteria {

	private String name;
	
	private String description;
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
