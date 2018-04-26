package p04_NumberInReversedOrder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        DecimalNumber number = new DecimalNumber(input);
        System.out.println(number.getReversedNumber());
    }
}
