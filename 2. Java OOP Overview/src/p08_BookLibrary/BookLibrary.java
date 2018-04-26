package p08_BookLibrary;

import java.util.ArrayList;
import java.util.List;

public class BookLibrary {
    private String name;
    public List<Book> books;

    public BookLibrary(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }
}
