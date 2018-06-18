package com.wang.springbootwebdemo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "users")     //指定和哪个表对应，若省略，表名是类名
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    String username;
    @Column
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
