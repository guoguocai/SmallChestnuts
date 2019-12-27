package com.example.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String name;

    private String uclass;

    private String email;

    private int age;

    private int dataStatus;

    public User() {
    }

    public User(String userId, String name, String uclass, String email, int age, int dataStatus) {
        this.userId = userId;
        this.name = name;
        this.uclass = uclass;
        this.email = email;
        this.age = age;
        this.dataStatus = dataStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUclass() {
        return uclass;
    }

    public void setUclass(String uclass) {
        this.uclass = uclass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", uclass='" + uclass + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", dataStatus=" + dataStatus +
                '}';
    }
}
