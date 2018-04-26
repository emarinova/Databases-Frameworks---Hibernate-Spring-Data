package p06_StudentsByPhone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    ArrayList<Student> students = new ArrayList<>();
    String[] input = reader.readLine().split(" ");

    while (!input[0].equals("END")){
        students.add(new Student(input[0], input[1], input[2]));
        input = reader.readLine().split(" ");
    }

    students.stream().filter(x -> x.getPhone().indexOf("02") == 0 || x.getPhone().indexOf("+3592") == 0)
            .forEach(x -> System.out.println(x.getFirstName() + " " + x.getLastName()));
}
}

class Student {
    private String firstName;
    private String lastName;
    private String phone;

    public Student(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }
}
