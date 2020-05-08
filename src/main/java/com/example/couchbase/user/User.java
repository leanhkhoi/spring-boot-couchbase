package com.example.couchbase.user;

import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import com.example.couchbase.DocumentType;

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
    // standard getters and setters
}
