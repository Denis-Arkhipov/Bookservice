package com.denar.bookservice.controllers;

import com.denar.bookservice.dto.BookDto;
import com.denar.bookservice.services.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        log.info("Start processing a request to get all books.");

        List<BookDto> books = bookService.readAll();
        log.info("Got a list of all books: {}", books);

        return books != null && !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookId(
            @PathVariable(name = "id", required = true) Long id) {
        log.info("Start processing a request to receive book by id: {}", id);

        BookDto book = bookService.read(id);
        log.info("Got a list of all book by id: {}", book);

        return book != null
                ? new ResponseEntity<>(book, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name")
    public ResponseEntity<List<BookDto>> getBooksByName(
            @RequestParam(value = "name", required = true) String name) {
        log.info("Start processing a request to receive books by name: {}", name);

        List<BookDto> books = bookService.readByName(name);
        log.info("Got a list of all books by name: {}", books);

        return books != null
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/authors/name")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(
            @RequestParam(value = "author", required = true) String name) {
        log.info("Start processing a request to receive books by author name: {}", name);

        List<BookDto> books = bookService.readByAuthor(name);
        log.info("Got a list of all books by author name: {}", books);

        return books != null
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/authors/{authorId}")
    public ResponseEntity<List<BookDto>> getBooksByAuthorId(
            @PathVariable(name = "authorId", required = true) Long authorId) {
        log.info("Start processing a request to receive book by author id: {}", authorId);

        List<BookDto> books = bookService.readByAuthorId(authorId);
        log.info("Got a list of all book by author id: {}", books);

        return books != null
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
