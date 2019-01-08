package com.future.phase2.spring.repository;

import com.future.phase2.spring.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

}
