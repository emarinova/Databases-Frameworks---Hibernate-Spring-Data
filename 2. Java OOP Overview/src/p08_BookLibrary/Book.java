package p08_BookLibrary;

import java.util.Date;

public class Book {
    public String title;
    public String author;
    public String publisher;
    public String releaseDate;
    public String ISBNNumber;
    public double price;

    public Book(String title, String author, String publisher, String releaseDate, String ISBNNumber, double price) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.releaseDate = releaseDate;
        this.ISBNNumber = ISBNNumber;
        this.price = price;
    }
}
