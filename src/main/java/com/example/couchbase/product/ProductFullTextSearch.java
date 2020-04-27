package com.example.couchbase.product;

import com.example.couchbase.AbstractCriteria;

public class ProductFullTextSearch extends AbstractCriteria {
	
	private String query;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
}
