package p07_AverageGrades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < n; i++){
            String inputData = reader.readLine();
            String[] input = inputData.split(" ");
            String name = input[0];
            Student temp = new Student(name);
            for(int j = 1; j < input.length; j++){
                temp.addToGrades(Double.parseDouble(input[j]));
            }
            students.add(temp);
        }
        Collections.sort(students, (s1, s2) -> s1.compareToAvG(s2));
        Collections.sort(students, (s1, s2) -> s1.compareToName(s2));
        //for(Student st : students){
        //    st.printStudent();
        //}

        students.stream().filter(s -> s.getAverageGrade() >= 5.00).forEach(s -> s.printStudent());


    }
}
