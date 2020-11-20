package com.example.BookStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private static final int PAGE_SIZE = 8;

    @Autowired
    BookRepository bookRepository;

    public void addBook(Book book){

        bookRepository.save(book);
    }

    public int getLastPage(){

        return (int)Math.ceil(new Double( (int)bookRepository.count() )/ PAGE_SIZE );
    }

    public int getPageCount(){
        return (int)Math.ceil(new Double( (int)bookRepository.count() )/ PAGE_SIZE );
    }

    public void deleteBookById(int id){
        bookRepository.deleteById(id);
    }

    public Book getBookById(int id){
        return  bookRepository.findById(id).get();
    }


    public List<Book> getSubBooks(int page){
         Pageable paging = PageRequest.of(page, PAGE_SIZE, Sort.by("id"));
         Page<Book> pagedResult = bookRepository.findAll(paging);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Book>();
        }

    }



}
