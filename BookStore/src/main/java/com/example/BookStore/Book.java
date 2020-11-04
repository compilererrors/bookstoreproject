package com.example.BookStore;

public class Book {
    private Integer id;
    private String title;
    private String author;
    private Double price;
    private String date;


    public Book(Integer id, String title, String author, Double price, String date) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.date = date;
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

}
