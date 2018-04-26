package p06_ShoppingSpree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.function.IntToDoubleFunction;

public class Main {
    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            LinkedHashSet<Person> peopleSet = new LinkedHashSet<>();
            ArrayList<Product> productsSet = new ArrayList<>();

            String line1 = reader.readLine();
            String[] people;
            String line2 = reader.readLine();
            String[] products;

            if (line1.contains(";")) {
                people = line1.split(";");
            } else {
                people = line1.split("=");
            }

            if (line2.contains(";")) {
                products = line2.split(";");
            } else {
                products = line2.split("=");
            }

            for (int i = 0; i < people.length; i++) {
                String name;
                double money;
                if (people[i].contains("=")) {
                    String[] peopleSplit = people[i].split("=");
                    name = peopleSplit[0];
                    money = Double.parseDouble(peopleSplit[1]);
                    peopleSet.add(new Person(name, money));
                } else {
                    name = people[i];
                    money = Double.parseDouble(people[i + 1]);
                    peopleSet.add(new Person(name, money));
                    break;
                }
            }

            for (int i = 0; i < products.length; i++) {
                String product;
                double price;
                if (products[i].contains("=")) {
                    String[] productSplit = products[i].split("=");
                    product = productSplit[0];
                    price = Double.parseDouble(productSplit[1]);
                    productsSet.add(new Product(product, price));
                } else {
                    product = products[i];
                    price = Double.parseDouble(products[i + 1]);
                    productsSet.add(new Product(product, price));
                    break;
                }
            }

            String command = reader.readLine();

            while (!command.equals("END")) {
                String[] buyProducts = command.split(" ");
                String name = buyProducts[0];
                String product = buyProducts[1];

                for (Person person : peopleSet) {
                    if (person.getName().equals(name)) {
                        for (Product product1 : productsSet) {
                            if (product1.getName().equals(product)) {
                                person.addToBag(product1.getName(), product1.getPrice());
                            }
                        }
                    }
                }


                command = reader.readLine();
            }
            for (Person person : peopleSet) {
                person.getPurchases();
            }

        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }
    }
}
