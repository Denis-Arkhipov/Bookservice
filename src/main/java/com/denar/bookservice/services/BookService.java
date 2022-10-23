package com.denar.bookservice.services;

import com.denar.bookservice.dto.BookDto;
import com.denar.bookservice.repositories.BookRepository;
import com.denar.bookservice.repositories.entityes.Book;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class BookService {
    private BookRepository repository;
    private ModelMapper mapper;

    public List<BookDto> readAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(b -> mapper.map(b, BookDto.class))
                .collect(Collectors.toList());
    }

    public BookDto read(Long id) {
        Optional<Book> book = repository.findById(id);
        return mapper.map(book, BookDto.class);
    }

    public void create(BookDto bookDto) {
        repository.save(mapper.map(bookDto, Book.class));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
