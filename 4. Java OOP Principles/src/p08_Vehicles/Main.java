package p08_Vehicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] carTokens = reader.readLine().split("\\s+");
        Vehicles car = new Car(Double.parseDouble(carTokens[1]), Double.parseDouble(carTokens[2]));
        String[] truckTokens = reader.readLine().split("\\s+");
        Vehicles truck = new Truck(Double.parseDouble(truckTokens[1]), Double.parseDouble(truckTokens[2]));


        int numberOfOperation = Integer.parseInt(reader.readLine());

        String pattern = "0.##############";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        while (numberOfOperation-- > 0) {
            String[] commandToken = reader.readLine().split("\\s+");

            if (commandToken.length != 3) {
                continue;
            }

            try {
                switch (commandToken[1]) {
                    case "Car":
                        double parameterC = Double.parseDouble(commandToken[2]);
                        if (isDrive(commandToken[0])) {
                            car.drive(parameterC);
                            System.out.println(String.format("Car travelled %s km", decimalFormat.format(parameterC)).replace(",", "."));
                        } else {
                            double fuel = Double.parseDouble(commandToken[2]);
                            car.refuel(fuel);
                        }
                        break;
                    case "Truck":
                        double parameterT = Double.parseDouble(commandToken[2]);
                        if (isDrive(commandToken[0])) {
                            truck.drive(parameterT);
                            System.out.println(String.format("Truck travelled %s km", decimalFormat.format(parameterT).replace(",", ".")));
                        } else {
                            double fuel = Double.parseDouble(commandToken[2]);
                            truck.refuel(fuel);
                        }
                        break;
                }
            }
            catch (IllegalStateException | IllegalArgumentException ise) {
                System.out.println(ise.getMessage());
            }
        }
        System.out.println(car.toString());
        System.out.println(truck.toString());
    }

    private static boolean isDrive (String s) {
        return "Drive".equalsIgnoreCase(s);
    }
}
