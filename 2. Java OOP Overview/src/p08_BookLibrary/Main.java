package p08_BookLibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        BookLibrary library = new BookLibrary("Library");

        for (int i = 0; i < n; i++){

            String[] input = reader.readLine().split("\\s");

            String name = input[0];
            String author = input[1];
            String publisher = input[2];
            String date = input[3];
            String ISBN = input[4];
            Double price = Double.parseDouble(input[5]);

            library.books.add(new Book(name, author, publisher, date, ISBN, price));
        }

        library.books.stream().
                collect(Collectors.groupingBy(book -> book.author, Collectors.summingDouble(book -> book.price)))
                .entrySet().stream().sorted(Map.Entry.comparingByKey()).sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(pair -> System.out.printf("%s -> %.2f%n", pair.getKey(), pair.getValue()));
    }
}
