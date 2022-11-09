package com.denar.bookservice.repositories.entityes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(schema = "public", name = "author")
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    private Long id;
    private String name;
}
