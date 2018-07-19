package com.wang.recruitwebsite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "allrecruit")
public class AllRecruit {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String post;
    @Column
    private String salary;
    @Column
    private String address;

    public AllRecruit(){}
    public AllRecruit(String id, String name, String post, String salary, String address) {
        this.id = id;
        this.name = name;
        this.post = post;
        this.salary = salary;
        this.address = address;
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
