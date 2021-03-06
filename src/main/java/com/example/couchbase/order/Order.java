package com.example.couchbase.order;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import com.example.couchbase.DocumentType;

@Document
public class Order {

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
    private DocumentType type = DocumentType.ORDER;

    @Field
    @NotNull
    private Integer quantity;

    @Field
    @NotNull
    private LocalDateTime created;

    @Field
    private LocalDateTime updated;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }
}
