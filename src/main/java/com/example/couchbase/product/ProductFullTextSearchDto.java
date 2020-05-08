package com.example.couchbase.product;

public class ProductFullTextSearchDto extends Product {

    private String matchContent;

    public String getMatchContent() {
	return matchContent;
    }

    public void setMatchContent(String matchContent) {
	this.matchContent = matchContent;
    }

}
