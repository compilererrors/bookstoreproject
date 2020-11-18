package com.example.BookStore;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findAllByOrderByAuthor();
    List<Book> findAllByOrderByAuthorDesc();

    List<Book> findByIdBetween(int from, int to);

}



