package com.example.couchbase.product;

import com.example.couchbase.AbstractCriteria;

public class ProductCriteria extends AbstractCriteria {

    private String name;

    private ProductCateogry category;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public ProductCateogry getCategory() {
        return category;
    }

    public void setCategory(ProductCateogry category) {
        this.category = category;
    }
    
}
