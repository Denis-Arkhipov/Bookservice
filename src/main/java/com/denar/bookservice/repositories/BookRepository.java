package com.denar.bookservice.repositories;

import com.denar.bookservice.repositories.entityes.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    @Query(value = "select * from books b where b.name like :name",
            nativeQuery = true)
    List<Book> findByName(@Param("name") String nameBook);

    @Query(value = "select * from public.books b " +
            "where b.author_id " +
            "= (select a.id from public.author a " +
            "where a.name like :name)",
            nativeQuery = true)
    List<Book> findByAuthor(@Param("name") String nameAuthor);

    @Query(value = "select * from books b where b.author_id = :id",
            nativeQuery = true)
    List<Book> findByAuthorId(@Param("id") Long authorId);
}
