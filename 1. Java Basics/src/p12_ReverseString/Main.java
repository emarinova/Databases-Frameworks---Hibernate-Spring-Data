package p12_ReverseString;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        for(int i = input.length()-1; i >=0 ; i--){
            System.out.print(input.charAt(i));
        }
    }
}
