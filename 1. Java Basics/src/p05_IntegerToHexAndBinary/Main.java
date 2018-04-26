package p05_IntegerToHexAndBinary;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        Integer Decimal = Integer.parseInt(console.nextLine());
        String Hex = Integer.toHexString(Decimal);
        String Binary = Integer.toBinaryString(Decimal);
        System.out.println(Hex.toUpperCase());
        System.out.println(Binary);
    }
}
