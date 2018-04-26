package p09_Animals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        while (true) {
            try {
                String line;
                if ("beast!".equalsIgnoreCase(line = reader.readLine())) {
                    break;
                }
                String[] tokens = reader.readLine().split("\\s+");

                Animal animal = null;

                switch (line) {
                    case "Cat":
                        animal = AnimalFactory.createCat(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
                        break;
                    case "Dog":
                        animal = AnimalFactory.createDog(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
                        break;
                    case "Frog":
                        animal = AnimalFactory.createFrog(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
                        break;
                    case "Kitten":
                        animal = AnimalFactory.createKitten(tokens[0], Integer.parseInt(tokens[1]));
                        break;
                    case "Tomcat":
                        animal = AnimalFactory.createTomcat(tokens[0], Integer.parseInt(tokens[1]));
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid input!");
                }
                System.out.println(String.format("%s", animal.getClass().getSimpleName()));
                System.out.println(String.format("%s %d %s", animal.getName(), animal.getAge(), animal.getGender()));
                System.out.println(animal.produceSound());

            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }
    }
}
