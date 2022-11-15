package com.denar.bookservice.services;

import com.denar.bookservice.dto.BookDto;
import com.denar.bookservice.repositories.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@AllArgsConstructor
public class BookService {
    private BookRepository repository;
    private BookConvertor mapper;

    public List<BookDto> getAllBooks() {
        log.debug("Getting all books from a repository.");
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(book -> mapper.convertToDto(book))
                .collect(Collectors.toList());
    }

    public BookDto getBook(Long id) {
        log.debug("Getting a book from a repository by id: {}", id);
        return repository.findById(id)
                .map(book -> mapper.convertToDto(book))
                .orElse(null);
    }

    public List<BookDto> getBooks(String name) {
        log.debug("Getting books from a repository by book name: {}", name);
        return repository.findByName(name).stream()
                .map(book -> mapper.convertToDto(book))
                .collect(Collectors.toList());
    }

    public List<BookDto> getBookByAuthor(String name) {
        log.debug("Getting books from a repository by author name: {}", name);
        return repository.findByAuthor(name).stream()
                .map(book -> mapper.convertToDto(book))
                .collect(Collectors.toList());
    }

    public List<BookDto> getBookByAuthorId(Long id) {
        log.debug("Getting books from a repository by author id: {}", id);
        return repository.findByAuthorId(id).stream()
                .map(book -> mapper.convertToDto(book))
                .collect(Collectors.toList());
    }
}
