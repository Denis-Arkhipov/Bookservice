package com.denar.bookservice.services;

import com.denar.bookservice.dto.BookDto;
import com.denar.bookservice.repositories.entityes.Book;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookConvertor {
    private final ModelMapper mapper;

    public BookDto convertToDto(Book book) {
        return mapper.map(book, BookDto.class);
    }
}
