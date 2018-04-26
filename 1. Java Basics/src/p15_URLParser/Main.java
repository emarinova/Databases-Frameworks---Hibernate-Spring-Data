package p15_URLParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();

        String protocol = "";
        String server = "";
        String resource = "";

        if (input.indexOf("://") > -1){
            protocol = input.substring(0,input.indexOf("://"));
            input = input.substring(input.indexOf("://")+3);
            if (input.indexOf("/") > -1){
                server = input.substring(0,input.indexOf("/"));
                resource = input.substring(input.indexOf("/")+1);
            } else {
                server = input;
            }
        } else {
            if (input.indexOf("/") > -1){
                server = input.substring(0,input.indexOf("/"));
                resource = input.substring(input.indexOf("/")+1);
            } else {
                server = input;
            }
        }

        System.out.printf("[protocol] = \"%s\"%n", protocol);
        System.out.printf("[server] = \"%s\"%n", server);
        System.out.printf("[resource] = \"%s\"%n", resource);
    }
}
