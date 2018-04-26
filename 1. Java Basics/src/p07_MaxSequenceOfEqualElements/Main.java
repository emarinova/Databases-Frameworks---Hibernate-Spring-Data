package p07_MaxSequenceOfEqualElements;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String[] input = console.nextLine().split(" ");
        int start = 0;
        int len = 1;

        int curStart = 0;
        int curLen = 1;

        for (int i = 0; i < input.length - 1; i++){
            curStart = i;
            curLen = 1;
            for (int j = i+1; j < input.length; j++){
                if (input[i].equals(input[j])){
                    curLen++;
                    if (j == input.length-1 && curLen>len){
                        len = curLen;
                        start = curStart;
                        i = j-1;
                    }
                } else {
                    if (curLen>len) {
                        len = curLen;
                        start = curStart;
                    }
                    i = j-1;
                    break;
                }
            }
        }
        for (int i = start; i < start + len; i++){
            System.out.print(input[i]);
            System.out.print(" ");
        }
    }
}
