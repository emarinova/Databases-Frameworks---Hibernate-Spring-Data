package p09_StudentsEnrolledIn2004or2005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Student> students = new ArrayList<>();
        String[] input = reader.readLine().split(" ");
        while(!input[0].equals("END")){
            Student temp = new Student(input[0]);
            for (int i = 1; i < input.length; i++) {
                temp.addGrade(i-1, Integer.parseInt(input[i]));
            }
            students.add(temp);
            input = reader.readLine().split(" ");
        }

        String year = students.get(0).yearEnrolled();
        String temp = "";
        students.stream().filter(x -> x.yearEnrolled().equals("14") || x.yearEnrolled().equals("15"))
                .forEach(x -> x.printGrades());
    }
}

class Student {
    private String facultyNumber;
    private int[] grades;

    public Student(String facultyNumber) {
        this.facultyNumber = facultyNumber;
        this.grades = new int[4];
    }

    public void addGrade(int pos, int grade){
        this.grades[pos] = grade;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void printGrades() {
        for (int i = 0; i < this.grades.length; i++) {
            System.out.print(this.grades[i] + " ");
        }
        System.out.println();
    }

    public String yearEnrolled() {
        String year = this.facultyNumber.substring(4);
        return year;
    }
}
