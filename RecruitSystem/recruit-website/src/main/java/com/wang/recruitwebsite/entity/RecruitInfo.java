package com.wang.recruitwebsite.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "recruitinfo")
public class RecruitInfo {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String address;
    @Column
    private String post;
    @Column
    private String salary;
    @Column
    private String language;
    @Column
    private String skill;
    @Column
    private String experience;
    @Column
    private String education;
    @Column
    private String extra;

    public RecruitInfo(String id, String name,String address, String post, String salary, String language, String skill, String experience, String education, String extra) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.post = post;
        this.salary = salary;
        this.language = language;
        this.skill = skill;
        this.experience = experience;
        this.education = education;
        this.extra = extra;
    }
    public RecruitInfo(){}
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
