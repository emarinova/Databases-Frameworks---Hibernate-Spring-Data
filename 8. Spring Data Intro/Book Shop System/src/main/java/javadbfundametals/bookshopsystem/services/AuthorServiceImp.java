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
    public List<String> findAllFirstNameEndsWith(String endsWith) {
        return this.authorRepository.findAllByFirstNameEndsWith(endsWith).stream()
                .map(a -> a.getFirstName() + " " + a.getLastName())
                .collect(Collectors.toList());
    }

    @Override
    public int copiesByAuthor(String name) {
        String[] names = name.split("\\s+");
        String fName = names[0];
        String lName = names[1];
        Author author = this.authorRepository.findByFirstNameEqualsAndLastNameEquals(fName, lName);
        int copies = 0;
        for(Book b : author.getBooks()){
            copies+=b.getCopies();
        }
        return copies;
    }


}
