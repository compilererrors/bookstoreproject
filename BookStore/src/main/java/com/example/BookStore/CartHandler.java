package com.example.BookStore;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CartHandler {




    private List<CartItem> cartItems;
    private double totalPrice=0;
    private int totalNumberOfItems=0;


    public CartHandler(){

        cartItems= new ArrayList<CartItem>();
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public int getTotalNumberOfItemsInCart(){
        totalNumberOfItems=0;
        for(CartItem item:cartItems){
            totalNumberOfItems+=item.getQty();
        }
        return totalNumberOfItems;

    }

    public Double getTotalPriceInCart(){
        totalPrice=0;
        for(int i=0;i<cartItems.size();i++){
            totalPrice+=cartItems.get(i).getBook().getPrice()*cartItems.get(i).getQty();
        }
        return totalPrice;
    }

    public CartItem getCartItem(int bookID){
        for (int i=0;i<cartItems.size();i++){
            int cartItemID = cartItems.get(i).getBook().getId();


            if(cartItemID == bookID){
                cartItems.get(i);

            }
        }
        return  cartItems.get(0);

    }

    public CartItem getCartItem(Book book){

        for (int i=0;i<cartItems.size();i++){
            int cartItemID = cartItems.get(i).getBook().getId();
            int bookId = book.getId();

            if(cartItemID == bookId){
                cartItems.get(i);

            }
        }
        return  cartItems.get(0);

    }

    public void addItemToCart(Book book){
        Boolean bookAlreadyExistsInCart=false;

        for (int i=0;i<cartItems.size();i++){
            int cartItemID = cartItems.get(i).getBook().getId();
            int bookId = book.getId();

            if(cartItemID == bookId){
                bookAlreadyExistsInCart=true;
                cartItems.get(i).addQty();
                break;
            }
        }
        if(!bookAlreadyExistsInCart)
            cartItems.add(new CartItem(book,1));
    }

    public void addItemQTY(int cartItemIndex){
        cartItems.get(cartItemIndex).addQty();

    }

    public void subItemQTY(int cartItemIndex){
        cartItems.get(cartItemIndex).subQty();
        if(cartItems.get(cartItemIndex).getQty()==0)
            cartItems.remove(cartItemIndex);

    }

}
