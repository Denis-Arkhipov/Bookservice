package com.denar.bookservice.config;

import com.denar.bookservice.dto.AuthorDto;
import com.denar.bookservice.dto.BookDto;
import com.denar.bookservice.repositories.entityes.Author;
import com.denar.bookservice.repositories.entityes.Book;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(Book.class, BookDto.class);
        mapper.createTypeMap(Author.class, AuthorDto.class);

        return mapper;
    }
}
