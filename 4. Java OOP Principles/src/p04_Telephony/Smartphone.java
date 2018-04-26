package p04_Telephony;

public class Smartphone implements Call, Browse {

    public Smartphone() {
    }

    @Override
    public String browse(String site) {
        if (site.matches("[^0-9]*")) {
            return "Browsing: " + site + "!";
        } else {
            return "Invalid URL!";
        }
    }

    @Override
    public String call(String number) {
        if (number.matches("[0-9]*")){
            return "Calling... " + number;
        } else {
            return "Invalid number!";
        }
    }
}
