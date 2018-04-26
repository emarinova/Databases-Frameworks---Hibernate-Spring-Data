package p14_CensorEmailAddress;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        String email = console.nextLine();
        String input = console.nextLine();
        String[] emailData = email.split("@");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < emailData[0].length(); i++){
            sb.append('*');
        }
        sb.append('@');
        sb.append(emailData[1]);
        String replacementEmail = sb.toString();
        String inputReplaced = input.replace(email, replacementEmail);
        System.out.println(inputReplaced);
    }
}
