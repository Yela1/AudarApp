package com.bezkoder.springjwt.models;


import javax.persistence.*;

@Entity
@Table(name = "letter")
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "letter")
    private String letter;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "sound_url")
    private String soundUrl;

    @Column(name = "text")
    private String text;

    public Letter() {
    }

    public Letter(String letter, String imageUrl, String soundUrl, String text){
        this.letter = letter;
        this.imageUrl = imageUrl;
        this.soundUrl = soundUrl;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSoundUrl() {
        return soundUrl;
    }

    public void setSoundUrl(String soundUrl) {
        this.soundUrl = soundUrl;
    }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }
}
