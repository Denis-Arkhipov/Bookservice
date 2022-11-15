package com.denar.bookservice.controllers;

import com.denar.bookservice.dto.BookDto;
import com.denar.bookservice.services.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        log.info("Start processing a request to get all books.");

        List<BookDto> books = bookService.getAllBooks();
        log.info("Got a list of all books: {}", books);

        return Objects.nonNull(books) && !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable(name = "id") Long id) {
        log.info("Start processing a request to receive book by id: {}", id);

        BookDto book = bookService.getBook(id);
        log.info("Got a book by id, name: {}", book);

        return Objects.nonNull(book)
                ? new ResponseEntity<>(book, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/name")
    public ResponseEntity<List<BookDto>> getBooksByName(@RequestParam(value = "name") String name) {
        log.info("Start processing a request to receive books by name: {}", name);

        List<BookDto> books = bookService.getBooks(name);
        log.info("Got a list of all books by name: {}", books);

        return books != null && !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<List<BookDto>> getBooksByAuthorId(@PathVariable(name = "id") Long id) {
        log.info("Start processing a request to receive book by author id: {}", id);

        List<BookDto> books = bookService.getBookByAuthorId(id);
        log.info("Got a list of all book by author id: {}", books);

        return books != null && !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/authors/name")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(@RequestParam(value = "name") String name) {
        log.info("Start processing a request to receive books by author name: {}", name);

        List<BookDto> books = bookService.getBookByAuthor(name);
        log.info("Got a list of all books by author name: {}", books);

        return books != null && !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
