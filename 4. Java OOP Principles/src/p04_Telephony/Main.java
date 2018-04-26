package p04_Telephony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] phoneNumbers = reader.readLine().split(" ");
        String[] webSites = reader.readLine().split(" ");

        Smartphone phone = new Smartphone();

        for (String number : phoneNumbers){
            System.out.println(phone.call(number));
        }
        for (String site : webSites){
            System.out.println(phone.browse(site));
        }
    }
}
