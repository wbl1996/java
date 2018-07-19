package com.wang.recruitwebsite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resume")
public class Resume {
    @Id
    private String id;
    @Column
    private String name;
    @Column
    private String major;
    @Column
    private String post;
    @Column
    private String language;
    @Column
    private String skill;
    @Column
    private String evaluation;
    @Column
    private String experience;
    @Column
    private String honor;

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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
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

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public Resume(String id, String name, String major, String post, String language, String skill, String evaluation, String experience, String honor) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.post = post;
        this.language = language;
        this.skill = skill;
        this.evaluation = evaluation;
        this.experience = experience;
        this.honor = honor;
    }
    public Resume(){}
}
