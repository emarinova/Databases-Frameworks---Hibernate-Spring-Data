package p13_FitStringIn20Chars;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();

        if(input.length()<20){
            System.out.println(PadRight(20,'*',input));
        } else {
            System.out.println(input.substring(0,20));
        }
    }

    public static String PadRight(int number, char symbol, String str){
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        for (int i = str.length(); i < number; i++){
            sb.append(symbol);
        }
        return  sb.toString();
    }
}
