package p03_ReverseCharacters;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        sb.append(console.nextLine());
        sb.append(console.nextLine());
        sb.append(console.nextLine());
        sb.reverse();
        System.out.println(sb.toString());
    }
}
