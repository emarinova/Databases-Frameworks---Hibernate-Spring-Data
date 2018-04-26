package p11_EqualSums;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        String[] input = console.nextLine().split(" ");
        int leftSum = 0;
        int rightSum = 0;
        boolean isThere = false;

        for(int i = 0; i < input.length; i++){
            leftSum = 0;
            rightSum = 0;
            for(int j = 0; j < i; j++){
                leftSum+=Integer.parseInt(input[j]);
            }
            for(int k = i+1; k < input.length; k++){
                rightSum+=Integer.parseInt(input[k]);
            }
            if(leftSum==rightSum){
                System.out.println(i);
                isThere = true;
                break;
            }
        }
        if(!isThere) System.out.println("no");
    }
}
