package javadbfundametals.bookshopsystem.services;

import javadbfundametals.bookshopsystem.entities.Author;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AuthorService {
    List<String> findAllFirstNameEndsWith(String endsWith);
    int copiesByAuthor(String name);
}
