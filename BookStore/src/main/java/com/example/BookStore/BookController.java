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
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private static final int PAGE_SIZE = 10;


    @Autowired
    private BookRepository repository;
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
    public String adminbook(Model model, @PathVariable Integer page, @PathVariable Integer id) {
        getBookPageAndId(model, page, id);

        return "adminbook";
    }

    @GetMapping("/adminbook/{page}/{id}/addBook")
    public String adminbookAdd(Model model, @PathVariable Integer page) {
        model.addAttribute("page", page);

        return "book";
    }

    @GetMapping("/book/{page}/{id}")
    public String book(Model model, @PathVariable Integer page, @PathVariable Integer id) {
        getBookPageAndId(model, page, id);

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

    /*@GetMapping("/book/{page}/{id}/addBook")
    public String addbook(Model model, @PathVariable Integer page, @PathVariable Integer id) {
        Book book = repository.getBook(id);
        model.addAttribute("page", page);
        model.addAttribute("book", book);

        return "book";
    }*/


    /*@PostMapping("/add")
    public String addToCart(HttpSession session, @ModelAttribute Book book, Model model){
        List<Book> cart = (List<Book>) session.getAttribute("cart");
        model.addAttribute("book", book);
        System.out.println(book.getId());


        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        cart.add(book);

        return "book";
    }*/


    /*@RequestMapping(value = "/addBook", method = RequestMethod.POST)
    public String submitBook(HttpSession session, @ModelAttribute("book") Book book, Model model) {
        List<Book> cart = (List<Book>) session.getAttribute("cart");
        model.addAttribute("book", book);
        System.out.println(book);


        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        cart.add(book);
        return "/book";
    }*/

    @RequestMapping(value = "/book/{page}/{id}/addBook", method = RequestMethod.POST)
    public String rateHandler(HttpSession session, HttpServletRequest request, @ModelAttribute("book") Book book, Model model) {
        cartHandler.addItemToCart(book);
        session.setAttribute("totalItems", cartHandler.getTotalNumberOfItemsInCart());
        session.setAttribute("cartHandler", cartHandler.getCartItems());

        System.out.println(book.getTitle());
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
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
            repository.addBook(book);

        int lastpage = repository.numberOfPages(PAGE_SIZE);

        return "redirect:/adminview";
    }

    @PostMapping("/editbook")
    public String editbook(@ModelAttribute Book book) {
        if (book.isNew()) {
            //Book newBook = restTemplate.postForObject("http://localhost:8080/book/", book, Book.class);
            repository.addBook(book); // todo replace with call POST /book (with book object as json in request body)
        }
        else {
            repository.editBook(book);
            // todo replace with call PUT /book/{id} (with book object as json in request body
            //restTemplate.put("http://localhost:8080/book/" + book.getId(), book, Book.class);
        }

        return "redirect:/";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        repository.deleteBook(id);
        return "redirect:/adminview";
    }



    /*@GetMapping("/startpage")
    public String startpage(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null && username.equals("admin")) {
            return "books";
        }
        return "books";
    }*/

    @GetMapping("/login")
    public String level1() {
        return "login";
    }


    /*@GetMapping("/logout")
    String logout(HttpSession session) {
        session.invalidate();
        return "login1";
    }*/

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
        List<Book> books = repository.getPage(page - 1, PAGE_SIZE);
        int pageCount = repository.numberOfPages(PAGE_SIZE);
        int[] pages = toArray(pageCount);

        model.addAttribute("books", books);
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("showPrev", page > 1);
        model.addAttribute("showNext", page < pageCount);

    }
    private void getBookPageAndId(Model model, int page, int id) {
        Book book = repository.getBook(id);
        model.addAttribute("page", page);
        model.addAttribute("book", book);
    }


}
