package com.denar.bookservice.services;

import com.denar.bookservice.dto.BookDto;
import com.denar.bookservice.repositories.BookRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@AllArgsConstructor
public class BookService {
    private BookRepository repository;
    private ModelMapper mapper;

    public List<BookDto> readAll() {
        log.debug("Getting all books from a repository.");
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(book -> mapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    public BookDto read(Long id) {
        log.debug("Getting a book from a repository by: {}", id);
        return repository.findById(id)
                .map(book -> mapper.map(book, BookDto.class))
                .orElse(null);
    }

    public List<BookDto> readByName(String name) {
        log.debug("Getting books from a repository book name: {}", name);
        return repository.findByName(name).stream()
                .map(book -> mapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    public List<BookDto> readByAuthor(String name) {
        log.debug("Getting books from a repository by author name: {}", name);
        return repository.findByAuthor(name).stream()
                .map(book -> mapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }

    public List<BookDto> readByAuthorId(Long authorId) {
        log.debug("Getting books from a repository by author id: {}", authorId);
        return repository.findByAuthorId(authorId).stream()
                .map(book -> mapper.map(book, BookDto.class))
                .collect(Collectors.toList());
    }
}
