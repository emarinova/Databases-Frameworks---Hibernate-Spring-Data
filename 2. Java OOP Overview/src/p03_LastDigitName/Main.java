package p03_LastDigitName;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int input = Integer.parseInt(console.nextLine());
        Number number = new Number(input);
        System.out.println(number.getEnglidshNameOfLastDigit());
    }
}
