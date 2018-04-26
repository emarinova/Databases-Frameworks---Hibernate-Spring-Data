package p05_IntersectionOfCircles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String[] input1 = console.nextLine().split(" ");
        String[] input2 = console.nextLine().split(" ");
        int x1 = Integer.parseInt(input1[0]);
        int y1 = Integer.parseInt(input1[1]);
        int r1 = Integer.parseInt(input1[2]);

        int x2 = Integer.parseInt(input2[0]);
        int y2 = Integer.parseInt(input2[1]);
        int r2 = Integer.parseInt(input2[2]);

        Circle c1 = new Circle(new Point(x1, y1), r1);
        Circle c2 = new Circle(new Point(x2, y2), r2);

        if (c1.intersect(c2)) System.out.println("Yes");
        else System.out.println("No");
    }
}
