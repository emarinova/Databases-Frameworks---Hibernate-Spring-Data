package p10_IndexOfLetters;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();

        for (int i = 0; i < input.length(); i++){
            System.out.printf("%s -> %d", input.charAt(i), (int)(input.charAt(i))-97);
            System.out.println();
        }
    }
}
