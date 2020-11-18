package com.example.BookStore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CartHandlerTests {


    private CartHandler cartHandler;



    private BookService service;

    @Before
    public void before() {
        cartHandler = new CartHandler();

    }

    @Test
    public void testAddBookToCart(){

        //CartHandler consists of a cartItem (book + qty)
        //If adding a new book qty of that book = 1 and totalqty = 1
        cartHandler.addItemToCart(service.getBookById(1));

        //total number of Items in cart
        Assert.assertEquals(1, cartHandler.getTotalNumberOfItemsInCart());

        //get number of Item for a specific book. In this case index 1 in carthandler
        Assert.assertEquals(1, cartHandler.getCartItems().get(0).getQty());

    }

    @Test
    public void testAddTwoOfTheSameBookToCart(){

        //CartHandler consists of a cartItem (book + qty)
        //If adding a new book qty of that book = 1 and totalqty = 1
        cartHandler.addItemToCart(service.getBookById(1));

        //total number of Items in cart
        Assert.assertEquals(1, cartHandler.getTotalNumberOfItemsInCart());

        //get number of Item for a specific book. In this case index 1 in carthandler
        Assert.assertEquals(1, cartHandler.getCartItems().get(0).getQty());

        //add same book again
        cartHandler.addItemToCart(service.getBookById(1));

        //total number of Items in cart
        Assert.assertEquals(2, cartHandler.getTotalNumberOfItemsInCart());

        //get number of Item for a specific book. In this case index 0 in carthandler
        Assert.assertEquals(2, cartHandler.getCartItems().get(0).getQty());

    }

    @Test
    public void testAddTwoDifferentBooksToCart(){

        //CartHandler consists of a cartItem (book + qty)
        //If adding a new book qty of that book = 1 and totalqty = 1
        cartHandler.addItemToCart(service.getBookById(1));

        //total number of Items in cart
        Assert.assertEquals(1, cartHandler.getTotalNumberOfItemsInCart());

        //get number of Item for a specific book. In this case index 0 in carthandler
        Assert.assertEquals(1, cartHandler.getCartItems().get(0).getQty());

        //add another book
        cartHandler.addItemToCart(service.getBookById(1));

        //total number of Items in cart
        Assert.assertEquals(2, cartHandler.getTotalNumberOfItemsInCart());

        //get number of Item for book1. In this case index 0 in carthandler
        Assert.assertEquals(1, cartHandler.getCartItems().get(0).getQty());

        //get number of Item for book 2. In this case index 1 in carthandler
        Assert.assertEquals(1, cartHandler.getCartItems().get(1).getQty());

    }

    @Test
    public void testAddAspecificBookDirectlyInCart(){

        //CartHandler consists of a cartItem (book + qty)
        //If adding a new book qty of that book = 1 and totalqty = 1
        cartHandler.addItemToCart(service.getBookById(1));

        //add the same book again from cart, i.e. call add method for a specific index. In this case index 0
        cartHandler.addItemQTY(0);

        //total number of Items in cart
        Assert.assertEquals(2, cartHandler.getTotalNumberOfItemsInCart());

        //get number of Item for book1. In this case index 0 in carthandler. Should be 2
        Assert.assertEquals(2, cartHandler.getCartItems().get(0).getQty());


    }


    @Test
    public void testRemoveQtyDirectlyInCart(){

       //1. Add book1
        cartHandler.addItemToCart(service.getBookById(1));

        //2. Add book1 from cart twice
        cartHandler.addItemQTY(0);
        cartHandler.addItemQTY(0);

        //3. Remove 1 from Book 1 from cart
        cartHandler.subItemQTY(0);

        //total number of Items in cart
        Assert.assertEquals(2, cartHandler.getTotalNumberOfItemsInCart());

        //get number of Item for book1. In this case index 0 in carthandler. Should be 2
        Assert.assertEquals(2, cartHandler.getCartItems().get(0).getQty());


    }

   /* @Test
    public void testCalulateTotalPrice(){

        This test will add some books + adding qty in order to match table below.
        Total price should match in order to pass test
        Book	qty	    Price per item	Total price
        ---------------------------------------------------
        201	    2	    42,99	        85,98
        205	    3	    46,99	        140,97
        218	    5	    59,99	        299,95
        223	    6	    64,99	        389,94
        256	    4	    97,99	        391,96
        234	    2	    75,99	        151,98
        ---------------------------------------------------
			                       Sum=	1460,78


        for (int i=0;i<2;i++)
            cartHandler.addItemToCart(repository.getBook(201));
        for (int i=0;i<3;i++)
            cartHandler.addItemToCart(repository.getBook(205));
        for (int i=0;i<5;i++)
            cartHandler.addItemToCart(repository.getBook(218));
        for (int i=0;i<6;i++)
            cartHandler.addItemToCart(repository.getBook(223));
        for (int i=0;i<4;i++)
            cartHandler.addItemToCart(repository.getBook(256));

        //last book also includes add book from cart test
        cartHandler.addItemToCart(repository.getBook(234)); //index 5
        cartHandler.addItemQTY(5);



        Assert.assertEquals(new Double(1460.78), cartHandler.getTotalPriceInCart());


    }
    @Test
    public void testGetCartItemQtyForDifferentBooks(){

        This test will add some books + adding qty in order to match table below.
        Total price should match in order to pass test
        Book	qty	    Price per item	Total price
        ---------------------------------------------------
        201	    2	    42,99	        85,98
        205	    3	    46,99	        140,97
        218	    5	    59,99	        299,95
        223	    6	    64,99	        389,94
        256	    4	    97,99	        391,96
        234	    2	    75,99	        151,98
        ---------------------------------------------------
			                       Sum=	1460,78


        for (int i=0;i<2;i++)
            cartHandler.addItemToCart(repository.getBook(201));
        for (int i=0;i<3;i++)
            cartHandler.addItemToCart(repository.getBook(205));
        for (int i=0;i<5;i++)
            cartHandler.addItemToCart(repository.getBook(218));
        for (int i=0;i<6;i++)
            cartHandler.addItemToCart(repository.getBook(223));
        for (int i=0;i<4;i++)
            cartHandler.addItemToCart(repository.getBook(256));

        //last book also includes add book from cart test
        cartHandler.addItemToCart(repository.getBook(234)); //index 5
        cartHandler.addItemQTY(5);


        Book    QTY
        201	    2
        205	    3
        218	    5
        223	    6
        256	    4
        234	    2


        Assert.assertEquals(2,cartHandler.getCartItem(201).getQty());
        Assert.assertEquals(3,cartHandler.getCartItem(205).getQty());
        Assert.assertEquals(5,cartHandler.getCartItem(218).getQty());
        Assert.assertEquals(6,cartHandler.getCartItem(223).getQty());
        Assert.assertEquals(4,cartHandler.getCartItem(256).getQty());
        Assert.assertEquals(2,cartHandler.getCartItem(234).getQty());




    }*/



        }
