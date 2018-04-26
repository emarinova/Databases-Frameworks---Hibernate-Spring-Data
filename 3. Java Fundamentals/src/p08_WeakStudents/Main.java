package p08_WeakStudents;

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
            Student temp = new Student(input[0], input[1]);
            for (int i = 2; i< input.length; i++){
                temp.addGrade(i-2, Integer.parseInt(input[i]));
            }
            students.add(temp);
            input = reader.readLine().split(" ");
        }
        students.stream().filter(x -> x.atLeastTwoUnder3() == true)
                .forEach(x -> System.out.printf("%s %s%n", x.getFirstName(), x.getLastName()));
    }
}

class Student {
    private String firstName;
    private String lastName;
    private int[] grades;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.grades = new int[4];
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int[] getGrades() {
        return grades;
    }

    public void addGrade(int position, int grade){
        this.grades[position] = grade;
    }

    public boolean atLeastTwoUnder3() {
        boolean isThereTwo = false;
        int counter = 0;
        for (int i = 0; i < this.grades.length; i++){
            if(this.grades[i] <= 3){
                counter++;
                if (counter == 2){
                    isThereTwo = true;
                    break;
                }
            }
        }
        return isThereTwo;
    }
}
