package com.future.phase2.spring.controller;

import com.future.phase2.spring.entity.Book;
import com.future.phase2.spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String helloWorld(){
        return "Hello World!";
    }

    @PostMapping("/book")
    public Book saveBook (@RequestBody Book book){
        return bookRepository.save(book);
    }

    @GetMapping("/book/{id}")
    @Cacheable(key = "#id", value = "book") // #id mengambil dari PathVariable id
    public Book findBook (@PathVariable("id") String id){
        return bookRepository.findOne(id);
    }

    @PutMapping("/book")
    @CacheEvict(value = "book", key = "#book.id")
    public Book updateBook (@RequestBody Book book){
        Book savedBook = bookRepository.findOne(book.getId());
        if (savedBook == null) {
            System.out.println("Id not found!");
            return null;
        }
        return  bookRepository.save(book);


    }
}
