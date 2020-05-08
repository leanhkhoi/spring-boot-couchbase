package com.example.couchbase.order;

import com.example.couchbase.AbstractCriteria;

public class OrderCriteria extends AbstractCriteria {

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
