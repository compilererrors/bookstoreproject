package com.example.BookStore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    private Double price;
    private String date;
    private String thumbnail;


    public Book(Integer id, String title, String author, Double price, String date, String thumbnail) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.date = date;
        this.thumbnail = thumbnail;
    }

    public Book() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew(){
        return this.id == null;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
