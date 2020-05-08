package com.example.couchbase.product;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Transient;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.query.FetchType;
import org.springframework.data.couchbase.core.query.N1qlJoin;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import com.example.couchbase.DocumentType;
import com.example.couchbase.rating.Rating;

@Document
public class Product {

    @Id
    private String id;

    @Field
    @NotNull
    private String name;

    @Field
    @NotNull
    private String description;

    @Field
    @NotNull
    private Long price;

    @Field
    @NotNull
    private Integer meanRating;

    @Field
    @NotNull
    private DocumentType type = DocumentType.PRODUCT;

    @Field
    @NotNull
    private ProductCateogry category = ProductCateogry.BOOK;

    @Field
    @NotNull
    private DateTime created;

    @Field
    private DateTime updated;

    @Transient
    private List<Rating> ratings = new ArrayList<Rating>();

    @Transient
    private String matchContent;

    public String getMatchContent() {
	return matchContent;
    }

    public void setMatchContent(String matchContent) {
	this.matchContent = matchContent;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

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

    public Long getPrice() {
	return price;
    }

    public void setPrice(Long price) {
	this.price = price;
    }

    public Integer getMeanRating() {
	return meanRating;
    }

    public void setMeanRating(Integer meanRating) {
	this.meanRating = meanRating;
    }

    public DocumentType getType() {
	return type;
    }

    public void setType(DocumentType type) {
	this.type = type;
    }

    public DateTime getCreated() {
	return created;
    }

    public void setCreated(DateTime created) {
	this.created = created;
    }

    public DateTime getUpdated() {
	return updated;
    }

    public void setUpdated(DateTime updated) {
	this.updated = updated;
    }

    public List<Rating> getRatings() {
	return ratings;
    }

    public void setRatings(List<Rating> ratings) {
	this.ratings = ratings;
    }

    public ProductCateogry getCategory() {
	return category;
    }

    public void setCategory(ProductCateogry category) {
	this.category = category;
    }

    // standard getters and setters
}
