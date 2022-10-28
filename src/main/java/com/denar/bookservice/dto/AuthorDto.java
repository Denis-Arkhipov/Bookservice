package com.denar.bookservice.dto;

import com.denar.bookservice.repositories.entityes.Book;
import lombok.Data;

import java.util.List;

@Data
public class AuthorDto {
    private Long id;
    private String name;
}
