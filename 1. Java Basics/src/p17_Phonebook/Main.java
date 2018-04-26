package p17_Phonebook;

import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        TreeMap<String, String> phonebook = new TreeMap<String, String>();
        String input = console.nextLine();
        while (!input.equals("END")){
            String[] inputData = input.split(" ");
            String name = "";
            String number = "";

            switch (inputData[0]){
                case "A":
                    name = inputData[1];
                    number = inputData[2];
                    phonebook.put(name, number);
                    break;
                case "S":
                    name = inputData[1];
                    if (phonebook.containsKey(name)){
                        System.out.printf("%s -> %s%n", name, phonebook.get(name));
                    } else {
                        System.out.printf("Contact %s does not exist.%n", name);
                    }

                    break;
                case "ListAll":
                    for(String k:phonebook.keySet()){
                        System.out.printf("%s -> %s%n", k, phonebook.get(k));
                    }
            }

            input = console.nextLine();
        }
    }
}
