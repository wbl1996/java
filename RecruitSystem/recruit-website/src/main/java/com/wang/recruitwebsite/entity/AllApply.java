package com.wang.recruitwebsite.entity;

import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "allapply")
public class AllApply {
    @Id
    private int id;
    @Column
    private String s_id;
    @Column
    private String s_name;
    @Column
    private String e_name;
    @Column
    private String post;
    @Column
    private boolean flag;

    public AllApply(){}
    public AllApply(int id, String s_id, String s_name, String e_name, String post, boolean flag) {
        this.id = id;
        this.s_id = s_id;
        this.s_name = s_name;
        this.e_name = e_name;
        this.post = post;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
