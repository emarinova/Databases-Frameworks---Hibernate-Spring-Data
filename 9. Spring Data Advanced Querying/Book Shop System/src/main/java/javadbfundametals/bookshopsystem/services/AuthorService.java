package javadbfundametals.bookshopsystem.services;

import javadbfundametals.bookshopsystem.entities.Author;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AuthorService {

    void saveAuthorIntoDb(Author author);

    List<Author> getAuthors();

    List<String> getAuthorsWithAtLeastOneBookBeforeYear(Date year);

    List<String> getAuthorsWithBookCount();

    List<String> getAllBooksFromGeorgePowellOrdered();
}
