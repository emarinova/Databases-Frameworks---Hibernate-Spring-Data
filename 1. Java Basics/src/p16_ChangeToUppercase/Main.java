package p16_ChangeToUppercase;

        import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        String result = input;

        while (input.indexOf("<upcase>")>-1 && input.indexOf("</upcase>")>-1){
            String toReplace = input.substring(input.indexOf("<upcase>")+8, input.indexOf("</upcase>"));
            String replaced = input.substring(input.indexOf("<upcase>"), input.indexOf("</upcase>")+9);
            input = input.replace(replaced, toReplace.toUpperCase());
        }
        System.out.println(input);
    }
}
