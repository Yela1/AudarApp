package com.bezkoder.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "text")
public class Text {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "second_text")
    private String secondText;

    @Column(name = "another_text")
    private String another_string;

    @JsonIgnore
    @Column(name = "username")
    private String user;

    public Text() {
    }

    public Text(String text){
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAnother_string() {
        return another_string;
    }

    public void setAnother_string(String another_string) {
        this.another_string = another_string;
    }

    public String getSecondText() {
        return secondText;
    }

    public void setSecondText(String secondText) {
        this.secondText = secondText;
    }
}
