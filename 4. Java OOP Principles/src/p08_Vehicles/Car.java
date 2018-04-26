package p08_Vehicles;

public class Car extends Vehicles {
    public Car(double fuelQuantity, double fuelConsumptionPerKm) {
        super(fuelQuantity, fuelConsumptionPerKm);
    }

    @Override
    protected void setFuelConsumptionPerKm(double fuelConsumptionPerKm) {
        super.setFuelConsumptionPerKm(fuelConsumptionPerKm + 0.9);
    }

    @Override
    public void drive(double distance) {
        double needFuel = super.getFuelConsumptionPerKm() * distance;
        if (needFuel > super.getFuelQuantity()) {
            throw new IllegalStateException("Car needs refueling");
        }
        super.setFuelQuantity(super.getFuelQuantity() - needFuel);
    }

    @Override
    public void refuel(double litres) {
        if(litres < 0) {
            throw new IllegalStateException("Fuel must be a positive number");
        }
        super.setFuelQuantity((super.getFuelQuantity() + litres));
    }
}
