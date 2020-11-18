package com.example.BookStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private static final int PAGE_SIZE = 10;

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

    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }

    public Book getBookById(Long id){
        return  bookRepository.findById(id).get();
    }



    public List<Book> getSubBooks(int page){
        int from = Math.max(1,page*PAGE_SIZE);
        int to = Math.min((int)bookRepository.count(), (page+1)*PAGE_SIZE);

              /*
                repository.getPage(page - 1, PAGE_SIZE);

            public List<Book> getPage(int page, int pageSize) {
        int from = Math.max(0,page*pageSize);
        int to = Math.min(books.size(),(page+1)*pageSize);

        return books.subList(from, to);
         */
        return (List<Book>)bookRepository.findByIdBetween(from,to);


    }
    //page-1,PAGE_SIZE
}
