package javadbfundametals.bookshopsystem.services;

import javadbfundametals.bookshopsystem.entities.Book;
import javadbfundametals.bookshopsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
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
    public void saveBookIntoDb(Book book) {
        this.bookRepository.saveAndFlush(book);
    }

    @Override
    public List<String> findAllBooksAfterYear(Date year) {
        return this.bookRepository.findAllByReleaseDateAfter(year)
                .stream()
                .map(b -> b.getTitle())
                .collect(Collectors.toList());
    }


}
