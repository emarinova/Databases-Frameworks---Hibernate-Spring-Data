package javadbfundametals.bookshopsystem.services;

import javadbfundametals.bookshopsystem.entities.Book;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface BookService {

    List<String> findAllBooksWithRestriction(String restriction);

    List<String> findAllBooksWithEdTypeAndCopies(String edType, Integer copp);

    List<String> findAllBooksWithPriceGreaterThanOrLessThan(BigDecimal price1, BigDecimal price2);

    List<String> findAllBooksNotReleasedOnYear(String year) throws ParseException;

    List<String> findAllBooksBeforeDate(String date) throws ParseException;

    List<String> findAllBooksAuthorLastNameStrartsWith(String startsWith);

    List<String> findAllBooksTitleContains(String data);

    int findAllBooksTitleLongerThan(int number);

    List<String> authorsAndCopies();

    String reducedBook(String title);

    Integer increaseBookCopies(String date, int copies) throws ParseException;

    Integer removeBooksWithCopiesLessThan(int copies);

    Integer getTotalBooksOfAuthor(String name);
}
