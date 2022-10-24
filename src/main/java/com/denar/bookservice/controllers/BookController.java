package com.denar.bookservice.controllers;

import com.denar.bookservice.dto.BookDto;
import com.denar.bookservice.repositories.entityes.Book;
import com.denar.bookservice.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookDto> books = bookService.readAll();

        return books != null && !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable(name = "id") Long id) {
        BookDto book = bookService.read(id);

        return book != null
                ? new ResponseEntity<>(book, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookDto bookDto) {
        bookService.create(bookDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@RequestBody BookDto bookDto,
                                        @PathVariable(name = "id") Long id) {
        boolean updated = bookService.update(bookDto, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(name = "id") Long id) {
        boolean deleted = bookService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
