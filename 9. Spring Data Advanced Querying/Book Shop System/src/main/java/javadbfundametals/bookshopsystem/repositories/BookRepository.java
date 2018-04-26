package javadbfundametals.bookshopsystem.repositories;

import javadbfundametals.bookshopsystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findAllByReleaseDateAfter(Date year);
    List<Book> findAllByReleaseDateBefore(Date year);
}
