package p04_VowelOrDigit;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        char input = console.nextLine().charAt(0);
        String vowels = "aeoui";
        if(Character.isDigit(input)) System.out.println("digit");
        else if(vowels.indexOf(input) >0 && vowels.indexOf(input)<vowels.length()) System.out.println("vowel");
        else System.out.println("other");
    }
}
