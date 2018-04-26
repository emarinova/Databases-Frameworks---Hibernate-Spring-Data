package p01_StudentsByGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> students = new HashMap<>();

        String[] input = reader.readLine().split(" ");

        while (!input[0].equals("END")){
            students.put(input[0].concat(" ").concat(input[1]), Integer.parseInt(input[2]));
            input = reader.readLine().split(" ");
        }

        students.entrySet().stream().filter(s -> s.getValue() == 2).sorted(Map.Entry.comparingByKey()).forEach(s -> System.out.println(s.getKey()));
    }
}
