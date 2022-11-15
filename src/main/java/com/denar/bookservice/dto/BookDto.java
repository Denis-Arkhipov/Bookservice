package com.denar.bookservice.dto;

import com.denar.bookservice.repositories.entityes.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Long bookId;
    private String bookName;
    private String bookDescription;
    private Author author;
}
