package p03_LastDigitName;

public class Number {
    private int num;

    public Number(int num) {
        this.num = num;
    }

    private int getLastDigit(){
       return this.num%10;
    };

    public String getEnglidshNameOfLastDigit(){
        String lastDigit = "";
        switch (this.getLastDigit()){
            case 1: lastDigit = "one"; break;
            case 2: lastDigit = "two"; break;
            case 3: lastDigit = "three"; break;
            case 4: lastDigit = "four"; break;
            case 5: lastDigit = "five"; break;
            case 6: lastDigit = "six"; break;
            case 7: lastDigit = "seven"; break;
            case 8: lastDigit = "eight"; break;
            case 9: lastDigit = "nine"; break;
            case 0: lastDigit = "zero"; break;
        }
        return lastDigit;
    }

}
