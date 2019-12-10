package com.example.lab_4sprg.domain;

import org.junit.Test;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Entity
@Transactional
public class group443 {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name1;
    private String sname1;
    private Integer age;

    private String fileName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;



    public group443() {
    }

    public group443(String name1, String sname1, Integer age, User user) {
        this.name1 = name1;
        this.sname1 = sname1;
        this.age = age;
        this.author=user;
    }

    public String getAuthorName(){
        return author!=null ? author.getUsername():"<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
    public String getSname1() {
        return sname1;
    }

    public void setSname1(String sname1) {
        this.sname1 = sname1;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}