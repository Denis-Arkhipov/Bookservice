package com.denar.bookservice.repositories;

import com.denar.bookservice.repositories.entityes.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    @Query(value = "SELECT * FROM books b WHERE b.name iLIKE %:name%",
            nativeQuery = true)
    List<Book> findByName(@Param("name") String bookName);

    @Query(value = "SELECT * FROM public.books b " +
        "WHERE b.author_id " +
        "IN(SELECT a.id FROM public.authors a " +
        "WHERE a.name iLIKE %:name%)",
        nativeQuery = true)
    List<Book> findByAuthor(@Param("name") String authorName);

    @Query(value = "SELECT * FROM books b WHERE b.author_id = :id",
            nativeQuery = true)
    List<Book> findByAuthorId(@Param("id") Long id);


}
