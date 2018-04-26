package javadbfundametals.bookshopsystem.repositories;

import javadbfundametals.bookshopsystem.entities.Book;
import javadbfundametals.bookshopsystem.entities.ReducedBook;
import javadbfundametals.bookshopsystem.enums.AgeRestriction;
import javadbfundametals.bookshopsystem.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByAgeRestriction(AgeRestriction restriction);
    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType edType, Integer copp);
    List<Book> findAllByPriceGreaterThanOrPriceLessThan(BigDecimal price1, BigDecimal price2);
    List<Book> findAllByReleaseDateIsNot(Date date);
    List<Book> findAllByReleaseDateBefore(Date date);
    List<Book> findAllByAuthor_LastNameStartsWith(String startsWith);
    ReducedBook findByTitle(String title);

    @Modifying
    @Query("update Book As b SET b.copies = b.copies +:copiex WHERE b.releaseDate>:relDate")
    int increaseBookCopies(@Param("relDate") Date date, @Param("copiex") int copies);

    @Modifying
    int removeBooksByCopiesLessThan(Integer copies);

    @Procedure(name = "usp_number_of_books", outputParameterName = "counter")
    Integer storedProcedure(@Param("first_name") String fName, @Param("last_name") String lName);
}
