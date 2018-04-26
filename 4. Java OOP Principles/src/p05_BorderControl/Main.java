package p05_BorderControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Identifiable> kingdomStuff = new ArrayList<>();

        String line;
        while (true) {
            if ("end".equalsIgnoreCase(line = reader.readLine())) {
                break;
            }
            String[] tokens = line.split("\\s+");
            Identifiable resident = null;
            switch (tokens.length) {
                case 2:
                    resident = new Robot(tokens[0], tokens[1]);
                    kingdomStuff.add(resident);
                    break;
                case 3:
                    resident = new Citizen(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
                    kingdomStuff.add(resident);
                    break;
            }
        }
        String fakeIdCode = reader.readLine();
        kingdomStuff.stream().filter(r -> r.getId().endsWith(fakeIdCode))
                .forEach(r -> System.out.println(r.getId()));
    }
}
