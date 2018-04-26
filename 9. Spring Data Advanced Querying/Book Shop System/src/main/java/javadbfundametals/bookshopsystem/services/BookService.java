package javadbfundametals.bookshopsystem.services;

import javadbfundametals.bookshopsystem.entities.Book;

import java.util.Date;
import java.util.List;

public interface BookService {

    void saveBookIntoDb(Book book);

    List<String> findAllBooksAfterYear(Date year);
}
