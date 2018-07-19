package com.wang.recruitwebsite.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "enterprise")
public class Enterprise {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String HR;
    @Column
    private String password;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHR() {
        return HR;
    }

    public void setHR(String HR) {
        this.HR = HR;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Enterprise(String id, String name, String address, String HR, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.HR = HR;
        this.password = password;
    }
    public Enterprise(){}
}
