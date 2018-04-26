package javadbfundametals.bookshopsystem.runners;

import javadbfundametals.bookshopsystem.entities.*;
import javadbfundametals.bookshopsystem.enums.AgeRestriction;
import javadbfundametals.bookshopsystem.enums.EditionType;
import javadbfundametals.bookshopsystem.services.AuthorService;
import javadbfundametals.bookshopsystem.services.BookService;
import javadbfundametals.bookshopsystem.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BookService bookService;

    private static final String AUTHORS_RESOURCE_FILE = "C:\\Programing\\Java DB Fundamentals\\Databases Advanced - Hibernate\\Spring Data Intro\\Book Shop System\\src\\main\\resources\\authors.txt";
    private static final String CATEGORIES_RESOURCE_FILE = "C:\\Programing\\Java DB Fundamentals\\Databases Advanced - Hibernate\\Spring Data Intro\\Book Shop System\\src\\main\\resources\\categories.txt";
    private static final String BOOKS_RESOURCE_FILE = "C:\\Programing\\Java DB Fundamentals\\Databases Advanced - Hibernate\\Spring Data Intro\\Book Shop System\\src\\main\\resources\\books.txt";

    @Override
    public void run(String... args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
   //1.
        //String restriction = reader.readLine();
        //printAllBooksWithAgeRestriction(restriction);
   //2.
        // printAllBooksWithEdTypeAndCopies();
   //3.
        // printAllBooksWithPriceGreaterThanOrLessThan();
   //4.
        //String year = reader.readLine();
        //printAllBooksWithDifferentReleaseDate(year);
   //5.
        //String date = reader.readLine();
        //printAllBooksBeforeDate(date);
   //6.
        //String input = reader.readLine();
        //printAllAuthorsFirstNameEndsWith(input);
   //7.
        //String input = reader.readLine();
        //printAllBooksTitleContains(input);
   //8.
        //String input = reader.readLine();
        //printAllBooksAuthorStartsWith(input);
   //9.
        //int number = Integer.parseInt(reader.readLine());
        //printAllBooksTitleIsLongerThan(number);
   //10.
        //printCopiesForAuthors();
   //11.
        //String title = reader.readLine();
        //printInfoAboutBook(title);
   //12.
        //String date = reader.readLine();
        //int copies = Integer.parseInt(reader.readLine());
        //increaseBookCopies(date, copies);
   //13.
        //int copies = Integer.parseInt(reader.readLine());
        //removeBooksWithCopiesLessThan(copies);
   //14.
        String author = reader.readLine();
        storedProcedure(author);


    }

    private void storedProcedure(String author) {
        System.out.println(author + " has written " + this.bookService.getTotalBooksOfAuthor(author) + " books");
    }

    private void removeBooksWithCopiesLessThan(int copies) {
        System.out.println(this.bookService.removeBooksWithCopiesLessThan(copies) + " books were deleted");
    }

    private void increaseBookCopies(String date, int copies) throws ParseException {
        System.out.println(this.bookService.increaseBookCopies(date, copies)*copies);
    }

    private void printInfoAboutBook(String title){
        System.out.println(this.bookService.reducedBook(title));
    }
    private void printCopiesForAuthors() {
        for (String a : this.bookService.authorsAndCopies()){
            System.out.println(a);
        }
    }

    //private void printCopiesForAuthor(String author) {
    //    System.out.printf("%s - %d%n", author, this.authorService.copiesByAuthor(author));
    //}

    private void printAllBooksTitleIsLongerThan(int number) {
        System.out.println(this.bookService.findAllBooksTitleLongerThan(number));
    }

    private void printAllBooksTitleContains(String input) {
        for (String book : this.bookService.findAllBooksTitleContains(input)){
            System.out.println(book);
        }
    }

    private void printAllBooksAuthorStartsWith(String input) {
        for (String book : this.bookService.findAllBooksAuthorLastNameStrartsWith(input)){
            System.out.println(book);
        }
    }

    private void printAllAuthorsFirstNameEndsWith(String input) {
        for (String author : this.authorService.findAllFirstNameEndsWith(input)){
            System.out.println(author);
        }
    }

    private void printAllBooksBeforeDate(String date) throws ParseException {
        for (String book : this.bookService.findAllBooksBeforeDate(date)){
            System.out.println(book);
        }
    }

    private void printAllBooksWithDifferentReleaseDate(String year) throws ParseException {
        for (String title : this.bookService.findAllBooksNotReleasedOnYear(year)){
            System.out.println(title);
        }
    }

    private void printAllBooksWithPriceGreaterThanOrLessThan() {
        for (String book : this.bookService.findAllBooksWithPriceGreaterThanOrLessThan(BigDecimal.valueOf(40), BigDecimal.valueOf(5))){
            System.out.println(book);
        }
    }

    private void printAllBooksWithEdTypeAndCopies() {
        for (String title : this.bookService.findAllBooksWithEdTypeAndCopies("GOLD", 5000)){
            System.out.println(title);
        }
    }

    private void printAllBooksWithAgeRestriction(String restriction) {
        for (String title : this.bookService.findAllBooksWithRestriction(restriction)) {
            System.out.println(title);
        }
    }

}