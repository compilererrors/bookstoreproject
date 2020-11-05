package com.example.BookStore;

public class CartItem {
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    private Book book;
    private int qty;

    public CartItem(Book book, int qty){
        this.book=book;
        this.qty=qty;
    }

    public void addQty(){
        qty+=1;

    }
    public void subQty(){
        qty-=1;

    }


}
