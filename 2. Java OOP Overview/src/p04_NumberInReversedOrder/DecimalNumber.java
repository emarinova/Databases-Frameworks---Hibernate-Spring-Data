package p04_NumberInReversedOrder;

public class DecimalNumber {
    private String number;

    public DecimalNumber(String number){
        this.number = number;
    }

    private String reverseNumber(){

        StringBuilder sb = new StringBuilder();
        for(int i = this.number.length()-1; i >= 0; i--){
            sb.append(this.number.charAt(i));
        }
        return sb.toString();
    }

    public String getReversedNumber(){
        return this.reverseNumber();
    }
}
