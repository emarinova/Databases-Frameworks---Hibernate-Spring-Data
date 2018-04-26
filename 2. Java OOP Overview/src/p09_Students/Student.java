package p09_Students;

public class Student {
    private String name;
    public static int counter = 0;

    public Student(String name){
        this.name = name;
        counter++;
    }
}
