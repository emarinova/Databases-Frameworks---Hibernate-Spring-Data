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
    private  AuthorService authorService;
    @Autowired
    private  CategoryService categoryService;
    @Autowired
    private  BookService bookService;

    private static final String AUTHORS_RESOURCE_FILE = "C:\\Programing\\Java DB Fundamentals\\Databases Advanced - Hibernate\\Spring Data Intro\\Book Shop System\\src\\main\\resources\\authors.txt";
    private static final String CATEGORIES_RESOURCE_FILE = "C:\\Programing\\Java DB Fundamentals\\Databases Advanced - Hibernate\\Spring Data Intro\\Book Shop System\\src\\main\\resources\\categories.txt";
    private static final String BOOKS_RESOURCE_FILE = "C:\\Programing\\Java DB Fundamentals\\Databases Advanced - Hibernate\\Spring Data Intro\\Book Shop System\\src\\main\\resources\\books.txt";

   // public Runner(AuthorService authorService, CategoryService categoryService, BookService bookService) {
   //     this.authorService = authorService;
   //     this.categoryService = categoryService;
   //     this.bookService = bookService;
   // }

    @Override
    public void run(String... args) throws Exception {
        //initAuthors();
        //initCategories();
        //initBooks();
        //printAllBookTitlesAfter2000();
        //printAllAuthorsNamesWithBookAfter1990();
        //printAllAuthorsWithBookCount();
        //printAllBooksByGeorgePowellOrdered();
    }

    private void printAllBooksByGeorgePowellOrdered() {
        for(String book : this.authorService.getAllBooksFromGeorgePowellOrdered()){
            System.out.println(book);
        }
    }

    private void printAllAuthorsWithBookCount() {
        for(String author : this.authorService.getAuthorsWithBookCount()){
            System.out.println(author);
        }
    }

    private void printAllAuthorsNamesWithBookAfter1990() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse("1990-01-01");
        for(String name : this.authorService.getAuthorsWithAtLeastOneBookBeforeYear(date)){
            System.out.println(name);
        }
    }

    private void printAllBookTitlesAfter2000() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse("2000-01-01");
        for(String title : this.bookService.findAllBooksAfterYear(date)){
            System.out.println(title);
        }
    }

    private void initAuthors() throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(AUTHORS_RESOURCE_FILE))));

        //InputStream stream = this.getClass().getResourceAsStream(AUTHORS_RESOURCE_FILE);
        //BufferedReader reader1 = new BufferedReader(new InputStreamReader(stream));

        List<String> authorsLines = Files.readAllLines(Paths.get(AUTHORS_RESOURCE_FILE));
        authorsLines.stream().map((String s) -> {
            String[] authorNames = s.split("\\s+");
            return new Author(authorNames[0], authorNames[1]);
        }).forEach(a -> authorService.saveAuthorIntoDb(a));

    }

    private void initCategories() throws IOException {
        List<String> allCategories = Files.readAllLines(Paths.get(CATEGORIES_RESOURCE_FILE));
        allCategories.stream().filter(s -> !s.isEmpty()).map((String s) -> {
            return new Category(s);
        }).forEach(c -> categoryService.saveCategoryIntoDb(c));

    }

    private void initBooks() throws IOException, ParseException {
        //BufferedReader booksReader = new BufferedReader(new FileReader(BOOKS_RESOURCE_FILE));
        List<String> allBooks = Files.readAllLines(Paths.get(BOOKS_RESOURCE_FILE));
        Random random = new Random();

        List<Author> authors = this.authorService.getAuthors();
        List<Category> categories = this.categoryService.getCategories();

        for ( String line : allBooks ) {

            String[] data = line.split("\\s+");

            int authorIndex = random.nextInt(authors.size());
            Author author = authors.get(authorIndex);

            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];

            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = formatter.parse(data[1]);

            int copies = Integer.parseInt(data[2]);

            BigDecimal price = new BigDecimal(data[3]);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];

            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < data.length; i++) {
                titleBuilder.append(data[i]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

            Book book = new Book();
            book.setAuthor(author);
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);


            Set<Category> categoriesForBook = new HashSet<>();
            int categoriesCount = random.nextInt(4);
            for (int i = 0; i < categoriesCount; i++) {
                int categoryIndex = random.nextInt(categories.size());
                categoriesForBook.add(categories.get(categoryIndex));
            }
            book.setCategories(categoriesForBook);

            bookService.saveBookIntoDb(book);
        }
    }
}
