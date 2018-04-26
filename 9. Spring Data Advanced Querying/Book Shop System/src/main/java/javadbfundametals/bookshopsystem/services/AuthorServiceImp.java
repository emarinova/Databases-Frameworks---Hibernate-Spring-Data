package javadbfundametals.bookshopsystem.services;

import javadbfundametals.bookshopsystem.entities.Author;
import javadbfundametals.bookshopsystem.entities.Book;
import javadbfundametals.bookshopsystem.repositories.AuthorRepository;
import javadbfundametals.bookshopsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorServiceImp implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorServiceImp(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void saveAuthorIntoDb(Author author) {
        this.authorRepository.saveAndFlush(author);
    }

    @Override
    public List<Author> getAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public List<String> getAuthorsWithAtLeastOneBookBeforeYear(Date year) {
        return this.bookRepository.findAllByReleaseDateBefore(year).stream().map(b -> b.getAuthor().getFirstName() + " " + b.getAuthor().getLastName()).distinct().collect(Collectors.toList());

    }

    public List<String> getAuthorsWithBookCount(){
        return this.authorRepository.findAll().stream().sorted((a1, a2) -> a2.getBooks().size() - a1.getBooks().size())
                .map(a -> a.getFirstName() + " " + a.getLastName() + " " + a.getBooks().size()).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllBooksFromGeorgePowellOrdered() {

        Comparator<Book> compByReleaseDate = Comparator.comparing(book -> book.getReleaseDate());
        Comparator<Book> compByTitle = Comparator.comparing(book -> book.getTitle());
        return this.authorRepository.findByFirstNameEqualsAndLastNameEquals("George", "Powell").getBooks().stream()
                .sorted(compByReleaseDate.reversed().thenComparing(compByTitle))
                .map(book -> book.getTitle() + " " + book.getReleaseDate() + " " + book.getCopies())
                .collect(Collectors.toList());
    }
}
