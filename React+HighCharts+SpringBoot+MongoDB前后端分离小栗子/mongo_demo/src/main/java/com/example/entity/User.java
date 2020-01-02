package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer name;

    private Integer uclass;

    private Integer email;

    private Integer age;

    private Integer dataStatus;

    public User() {
    }

    public User(Integer userId, Integer name, Integer uclass, Integer email, Integer age, Integer dataStatus) {
        this.userId = userId;
        this.name = name;
        this.uclass = uclass;
        this.email = email;
        this.age = age;
        this.dataStatus = dataStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Integer getUclass() {
        return uclass;
    }

    public void setUclass(Integer uclass) {
        this.uclass = uclass;
    }

    public Integer getEmail() {
        return email;
    }

    public void setEmail(Integer email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name=" + name +
                ", uclass=" + uclass +
                ", email=" + email +
                ", age=" + age +
                ", dataStatus=" + dataStatus +
                '}';
    }
}
