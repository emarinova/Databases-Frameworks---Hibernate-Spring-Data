package javadbfundametals.bookshopsystem.services;

import javadbfundametals.bookshopsystem.entities.Author;
import javadbfundametals.bookshopsystem.entities.Book;
import javadbfundametals.bookshopsystem.entities.ReducedBook;
import javadbfundametals.bookshopsystem.enums.AgeRestriction;
import javadbfundametals.bookshopsystem.enums.EditionType;
import javadbfundametals.bookshopsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<String> findAllBooksWithRestriction(String restriction) {
        AgeRestriction restr = AgeRestriction.valueOf(restriction.toUpperCase());
        return this.bookRepository.findAllByAgeRestriction(restr).stream().map(b -> b.getTitle()).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksWithEdTypeAndCopies(String edType, Integer copp) {
        EditionType editionType = EditionType.valueOf(edType.toUpperCase());
        return this.bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType, copp).stream().map(b -> b.getTitle()).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksWithPriceGreaterThanOrLessThan(BigDecimal price1, BigDecimal price2) {
        return this.bookRepository.findAllByPriceGreaterThanOrPriceLessThan(price1, price2).stream()
                .map(b -> b.getTitle() + " - $" + b.getPrice()).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksNotReleasedOnYear(String year) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = formatter.parse("01-01-" + year);
        return this.bookRepository.findAllByReleaseDateIsNot(date).stream().map(b -> b.getTitle()).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksBeforeDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date dateD = formatter.parse(date);
        return this.bookRepository.findAllByReleaseDateBefore(dateD).stream()
                .map(b -> b.getTitle() + " - " + b.getEditionType() + " - $" + b.getPrice())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksAuthorLastNameStrartsWith(String startsWith) {
        StringBuilder sb = new StringBuilder();
        sb.append(startsWith.substring(0, 1).toUpperCase()).append(startsWith.substring(1).toLowerCase());
        return this.bookRepository.findAllByAuthor_LastNameStartsWith(sb.toString()).stream()
                .map(b -> b.getTitle() + " (" + b.getAuthor().getFirstName() + " " + b.getAuthor().getLastName() + ")")
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksTitleContains(String data) {
        List<Book> allBooks = this.bookRepository.findAll();
        List<Book> titleContains = allBooks.stream().filter(b -> b.getTitle().toLowerCase().contains(data.toLowerCase())).collect(Collectors.toList());
        return titleContains.stream().map(b -> b.getTitle()).collect(Collectors.toList());
    }

    @Override
    public int findAllBooksTitleLongerThan(int number) {
        List<Book> allBooks = this.bookRepository.findAll();
        List<Book> titleLongerThan = allBooks.stream().filter(b -> b.getTitle().length() > number).collect(Collectors.toList());
        return titleLongerThan.size();
    }

    @Override
    public List<String> authorsAndCopies() {
        Map<Author, Integer> authorsAndCopies = this.bookRepository.findAll().stream().collect(Collectors.groupingBy(b -> b.getAuthor(), Collectors.summingInt(b -> b.getCopies())));
        return authorsAndCopies.entrySet().stream()
                .sorted((a1, a2) -> a2.getValue() - a1.getValue())
                .map(a -> a.getKey().getFirstName() + " " + a.getKey().getLastName() + " - " + a.getValue())
                .collect(Collectors.toList());
    }

    @Override
    public String reducedBook(String title) {
        ReducedBook book = this.bookRepository.findByTitle(title);
        StringBuilder sb = new StringBuilder();
        sb.append(book.getTitle()).append(" ")
                .append(book.getEditionType()).append(" ")
                .append(book.getAgeRestriction()).append(" ")
                .append(book.getPrice());
        return sb.toString();
    }

    @Override
    public Integer increaseBookCopies(String date, int copies) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
        Date date1 = formatter.parse(date);
        return this.bookRepository.increaseBookCopies(date1, copies);
    }

    @Override
    public Integer removeBooksWithCopiesLessThan(int copies) {
        return this.bookRepository.removeBooksByCopiesLessThan(copies);
    }

    @Override
    public Integer getTotalBooksOfAuthor(String name) {
        String[] fullname = name.split(" ");
        int counter = 0;
        return this.bookRepository.storedProcedure(fullname[0], fullname[1]);
    }


}
