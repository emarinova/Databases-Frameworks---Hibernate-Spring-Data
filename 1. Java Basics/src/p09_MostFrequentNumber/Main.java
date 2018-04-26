package p09_MostFrequentNumber;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String[] input = console.nextLine().split(" ");
        int numPos = 0;
        int numTimes = 1;

        int curPos = 0;
        int curTimes = 1;

        for (int i = 0; i < input.length - 1; i++){
            curPos = i;
            curTimes = 1;
            for (int j = 0; j < input.length; j++){
                if (Integer.parseInt(input[i]) == Integer.parseInt(input[j])){
                    curTimes++;
                }
            }
            if (curTimes>numTimes){
                numTimes = curTimes;
                numPos = curPos;
            }
        }

        System.out.println(input[numPos]);
    }
}
