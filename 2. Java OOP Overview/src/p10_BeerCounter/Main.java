package p10_BeerCounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input[] = reader.readLine().split(" ");
        try {
            while (!input[0].equals("End")) {
                BeerCounter.buyBeer(Integer.parseInt(input[0]));
                BeerCounter.drinkBeer(Integer.parseInt(input[1]));
                input = reader.readLine().split(" ");
            }
            System.out.printf("%d %d", BeerCounter.getBeerInStock(), BeerCounter.getBeersDrankCount());
        } catch (Exception e) {
            System.out.printf("%d %d", BeerCounter.getBeerInStock(), BeerCounter.getBeersDrankCount());
        }
    }
}
