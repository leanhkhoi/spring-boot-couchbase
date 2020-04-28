package com.example.couchbase.user;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.query.N1qlJoin;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import com.example.couchbase.DocumentType;
import com.example.couchbase.rating.Rating;

@Document
public class User {

    @Id
    private String id;

    @Field
    @NotNull
    private String name;

    @Field
    @NotNull
    private Integer age;

    @Field
    @NotNull
    private Gender sex = Gender.MALE;

    @Field
    @NotNull
    private List<String> interestingProducts = new ArrayList<String>();

    @N1qlJoin(on = "lks.name=rks.user")
    private List<Rating> ratings = new ArrayList<Rating>();

    @Field
    @NotNull
    private DocumentType type = DocumentType.USER;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public List<String> getInterestingProducts() {
        return interestingProducts;
    }

    public void setInterestingProducts(List<String> interestingProducts) {
        this.interestingProducts = interestingProducts;
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

    // standard getters and setters
}
