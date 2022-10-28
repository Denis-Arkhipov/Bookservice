package com.denar.bookservice.dto;

import com.denar.bookservice.repositories.entityes.Page;
import lombok.Data;

import javax.persistence.OneToMany;
import java.util.List;

@Data
public class BookDto {
    private Long id;
    private String bookName;
    private Long authorId;
}
