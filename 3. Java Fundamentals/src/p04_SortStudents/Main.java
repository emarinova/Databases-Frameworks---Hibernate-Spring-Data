package p04_SortStudents;

import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.ArrayList;
        import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Student> students = new ArrayList<>();

        String input[] = reader.readLine().split(" ");
        while (!input[0].equals("END")){
            Student temp = new Student(input[0], input[1]);
            students.add(temp);
            input = reader.readLine().split(" ");
        }

        Comparator<Student> compareByFirstNameAsc = (x, y) -> x.getFirstName().compareTo(y.getFirstName());

        Comparator<Student> compareByLastNameAsc = (x, y) -> x.getLastName().compareTo(y.getLastName());

        students.stream().sorted(compareByLastNameAsc.thenComparing(compareByFirstNameAsc.reversed()))
                .forEach(x -> System.out.println(x.getFirstName() + " " + x.getLastName()));
    }
}


class Student {
    private String firstName;
    private String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}