package p03_StudentsByAge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Student> students = new ArrayList<>();
        String[] input = reader.readLine().split(" ");
        while (!input[0].equals("END")){
            String firstName = input[0];
            String lastName = input[1];
            Integer age = Integer.parseInt(input[2]);
            Student temp = new Student(firstName, lastName, age);
            students.add(temp);
            input = reader.readLine().split(" ");
        }
        students.stream().filter(x -> x.getAge() >= 18 && x.getAge() <= 24)
                .forEach(x -> System.out.printf("%s %s %d%n", x.getFirstName(), x.getLastName(), x.getAge()));
    }
}