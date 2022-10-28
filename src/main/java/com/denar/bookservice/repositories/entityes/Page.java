package com.denar.bookservice.repositories.entityes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(schema = "public", name = "page")
public class Page {
    @Id
    private Long id;
    private Long bookId;
    private int pageNumber;
    private String content;
}
