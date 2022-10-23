package com.denar.bookservice.repositories;

import com.denar.bookservice.repositories.entityes.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
