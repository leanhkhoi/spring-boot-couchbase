package com.example.couchbase.rating;

import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import com.example.couchbase.DocumentType;

@Document
public class Rating {

    @Id
    private String id;

    @Field
    @NotNull
    private String user;

    @Field
    @NotNull
    private String product;

    @Field
    @NotNull
    private DocumentType type = DocumentType.RATING;

    @Field
    @NotNull
    private Double point;

    @Field
    @NotNull
    private DateTime created;

    @Field
    private DateTime updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
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

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

}
