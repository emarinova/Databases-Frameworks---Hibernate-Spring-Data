package p07_AverageGrades;

import java.util.*;

public class Student {
    private String name;
    private ArrayList<Double> grades;
    private double averageGrade;

    public Student(String name){
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public void addToGrades(double grade){
        this.grades.add(grade);
    }

    //private void setAverageGrade(){
    //     this.averageGrade = 0;
    //     for(int i = 0; i < grades.size(); i++){
    //         this.averageGrade += grades.get(i);
    //     }
    //     this.averageGrade = this.averageGrade / grades.size();
    //}

    public double getAverageGrade(){
        this.averageGrade = 0;
        for(int i = 0; i < grades.size(); i++){
            this.averageGrade += grades.get(i);
        }
        this.averageGrade = this.averageGrade / grades.size();
        return this.averageGrade;
    }

    public String getName(){
        return this.name;
    }

    public void printStudent(){
        System.out.printf("%s -> %.2f%n", this.name, this.getAverageGrade());
    }

    public int compareToName(Student comparesto){
        String compareName = comparesto.getName();
        return this.getName().compareTo(compareName);
    }

    public int compareToAvG(Student compareto){
        double compareAvG = compareto.getAverageGrade();
        if (compareAvG>this.getAverageGrade()) return 1;
        else return -1;
    }


}
