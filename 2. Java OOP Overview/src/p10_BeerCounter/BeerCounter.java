package p10_BeerCounter;

public class BeerCounter {
    private static int beerInStock = 0;
    private static int beersDrankCount = 0;

    public static void buyBeer(int bottlesCount){

        beerInStock += bottlesCount;
    }

    public static void drinkBeer(int bottlesCount){
        beerInStock -= bottlesCount;
        beersDrankCount += bottlesCount;
    }

    public static int getBeerInStock() {
        return beerInStock;
    }

    public static int getBeersDrankCount() {
        return beersDrankCount;
    }
}
