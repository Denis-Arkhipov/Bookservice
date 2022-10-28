package com.denar.bookservice.repositories.entityes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "public", name = "author")
public class Author {
    @Id
    private Long id;
    private String name;
}
