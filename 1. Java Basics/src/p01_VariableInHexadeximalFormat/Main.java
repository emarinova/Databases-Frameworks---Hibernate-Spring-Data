package p01_VariableInHexadeximalFormat;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        String hexNumber = console.nextLine();

        Integer decNumber = Integer.parseInt(hexNumber, 16);

        System.out.println(decNumber);
    }
}

