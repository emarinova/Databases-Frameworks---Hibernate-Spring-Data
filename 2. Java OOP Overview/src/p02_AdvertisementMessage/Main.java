package p02_AdvertisementMessage;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String[] phrases = {"Excellent product.", "Such a great product.", "I always use that product.", "Best product of its category.",
                "Exceptional product.", "I can’t live without this product."};
        String[] events = {"Now I feel good.”, “I have succeeded with this product.", "Makes miracles. I am happy of the results!",
                "I cannot believe but now I feel awesome.", "Try it yourself, I am very satisfied.", "I feel great!"};
        String[] authors = {"Diana", "Petya", "Stella", "Elena", "Katya", "Iva", "Annie", "Eva"};
        String[] cities = {"Burgas", "Sofia", "Plovdiv", "Varna", "Ruse"};
        Random rnd = new Random();

        int times = Integer.parseInt(console.nextLine());

        for (int i = 0; i < times; i++) {
            int phrase = rnd.nextInt(phrases.length);
            int event = rnd.nextInt(events.length);
            int author = rnd.nextInt(authors.length);
            int city = rnd.nextInt(cities.length);
            System.out.printf("%s %s %s - %s%n", phrases[phrase], events[event], authors[author], cities[city]);
        }
    }
}
