package p05_BirthdayCelebration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Birthable> kingdomStuff = new ArrayList<>();

        String line;
        while (true) {
            if ("end".equalsIgnoreCase(line = reader.readLine())) {
                break;
            }
            String[] tokens = line.split("\\s+");

            switch (tokens[0]) {
                case "Robot":
                    Robot robot = new Robot(tokens[1], tokens[2]);
                    break;
                case "Citizen":
                    Birthable citizen = new Citizen(tokens[1], Integer.parseInt(tokens[2]), tokens[3], tokens[4]);
                    kingdomStuff.add(citizen);
                    break;
                case "Pet":
                    Birthable pet = new Pet(tokens[1], tokens[2]);
                    kingdomStuff.add(pet);
                    break;
            }
        }
        String neededYear = reader.readLine();
        kingdomStuff.stream().filter(r -> r.getBirthdate().endsWith(neededYear))
                .forEach(r -> System.out.println(r.getBirthdate()));
    }
}