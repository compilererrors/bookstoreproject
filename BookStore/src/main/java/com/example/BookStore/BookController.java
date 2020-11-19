package com.example.BookStore;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    List<Book> books;


    @Autowired
    private BookService service;

    @Autowired
    private CartHandler cartHandler;

    @GetMapping("/")
    public String books(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        getBooks(model, page);

        return "books";
    }

    @GetMapping("/adminview")
    public String adminbooks(Model model, @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        getBooks(model, page);

        return "adminbooks";
    }
    @GetMapping("/adminbook/{page}/{id}")
    public String adminbook(Model model, @PathVariable Integer page, @PathVariable int id) {
        getBookPageAndId(model, page, id);

        return "adminbook";
    }

    @GetMapping("/adminbook/{page}/{id}/addBook")
    public String adminbookAdd(Model model, @PathVariable Integer page) {
        model.addAttribute("page", page);

        return "book";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        Book book = service.getBookById(id);
        model.addAttribute("book" ,book);
        return "adminadd";
    }



    @GetMapping("/book/{page}/{id}")
    public String book(Model model, @PathVariable Integer page, @PathVariable int id) {
        model.addAttribute("page", page);
        model.addAttribute("book", service.getBookById(id));
        return "book";
    }


    @GetMapping("/book/{page}/{id}/addBook")
    public String registerUser(Model model, @PathVariable Integer page) {
        model.addAttribute("page", page);

        return "book";
    }

    @RequestMapping(value = "/book/{page}/{id}/back", method = RequestMethod.POST)
    public String back(HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        return "redirect:" + "'/?page=' + ${page}";
    }


    @RequestMapping(value = "/adminbook/{page}/{id}/addBook", method = RequestMethod.POST)
    public String rateHandler2(HttpSession session, HttpServletRequest request, @ModelAttribute("book") Book book, Model model, @PathVariable int id) {
        cartHandler.addItemToCart((Book)service.getBookById(id));
        session.setAttribute("totalItems", cartHandler.getTotalNumberOfItemsInCart());
        session.setAttribute("cartHandler", cartHandler.getCartItems());

        System.out.println(book.getTitle());
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }


    @RequestMapping(value = "/book/{page}/{id}/addBook", method = RequestMethod.POST)
    public String rateHandler(HttpSession session, HttpServletRequest request, @ModelAttribute("book") Book book, Model model, @PathVariable int id) {
        cartHandler.addItemToCart((Book)service.getBookById(id));
        session.setAttribute("totalItems", cartHandler.getTotalNumberOfItemsInCart());
        session.setAttribute("cartHandler", cartHandler.getCartItems());

        System.out.println(book.getTitle());
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public String rateHandler22(HttpSession session, HttpServletRequest request, @ModelAttribute("book") Book book, Model model) {
        cartHandler.addItemToCart(book);
        session.setAttribute("totalItems", cartHandler.getTotalNumberOfItemsInCart());
        session.setAttribute("cartHandler", cartHandler.getCartItems());

        System.out.println(book.getTitle());
        String referer = request.getHeader("referer");
        return "redirect:/" + referer;
    }

    @PostMapping("/addExistingBook")
        public String addOneExistingBook(HttpSession session, HttpServletRequest request, int cartItemIndex){

        cartHandler.addItemQTY(cartItemIndex);
        session.setAttribute("totalprice", Math.ceil(cartHandler.getTotalPriceInCart()*100)/100);
        session.setAttribute("totalItems", cartHandler.getTotalNumberOfItemsInCart());

        return "shopcart";
    }

    @PostMapping("/subExistingBook")
    public String subOneExistingBook(HttpSession session, HttpServletRequest request, int cartItemIndex){

        cartHandler.subItemQTY(cartItemIndex);
        session.setAttribute("totalprice", Math.ceil(cartHandler.getTotalPriceInCart()*100)/100);
        session.setAttribute("totalItems", cartHandler.getTotalNumberOfItemsInCart());

        return "shopcart";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("book", new Book());
        return "adminadd";
    }

    @PostMapping("/save")
    public String set(@ModelAttribute Book book) {
            service.addBook(book);

        int lastage = service.getLastPage();

        return "redirect:/adminview";
    }

    @PostMapping("/editbook")
    public String editbook(@ModelAttribute Book book) {
        service.addBook(book);
        return "redirect:/";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        service.deleteBookById(id);
        return "redirect:/adminview";
    }


    @GetMapping("/login")
    public String level1() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "userform";
    }


    @GetMapping("/shopcart")
    public String shopcart(HttpSession session) {
        session.setAttribute("cartHandler", cartHandler.getCartItems());
        session.setAttribute("totalprice", Math.ceil(cartHandler.getTotalPriceInCart()*100)/100);
        session.setAttribute("totalItems", cartHandler.getTotalNumberOfItemsInCart());
        return "shopcart";
    }


    @GetMapping("/orders")
    public String orders(HttpSession session) {

        return "orders";
    }


    private int[] toArray(int num) {
        int[] result = new int[num];
        for (int i = 0; i < num; i++) {
            result[i] = i + 1;
        }
        return result;
    }

    private void getBooks(Model model, int page) {
        books = service.getSubBooks(page-1) ;
        int pageCount = service.getPageCount();
        int[] pages = toArray(pageCount);
        model.addAttribute("books", books);
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("showPrev", page > 1);
        model.addAttribute("showNext", page < pageCount);

    }

    private void getBookPageAndId(Model model, int page, int id) {
        Book book = service.getBookById(id);

        model.addAttribute("page", page);
        model.addAttribute("book", book);
    }


}
