package p02_StudentsByFirstAndLastName;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        ArrayList<String[]> students = new ArrayList<>();
        while (!input[0].equals("END")){
            students.add(input);
            input = reader.readLine().split(" ");
        }
        String empty = "";
        students.stream().filter(x -> x[0].compareTo(x[1]) <= 0)
                .forEach(x -> System.out.println(x[0] + " " + x[1]));
    }
}
