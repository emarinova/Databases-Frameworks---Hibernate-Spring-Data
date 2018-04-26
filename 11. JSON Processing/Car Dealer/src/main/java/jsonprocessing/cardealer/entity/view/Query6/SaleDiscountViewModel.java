package jsonprocessing.cardealer.entity.view.Query6;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Set;

public class SaleDiscountViewModel implements Serializable {
    private long carId;
    @Expose
    private CarViewModel car;
    @Expose
    private String customerName;
    @Expose
    private double discount;
    @Expose
    private double price;
    @Expose
    private double priceWithDiscount;

    public SaleDiscountViewModel(long carId, String customerName, double discount, double price) {
        this.carId = carId;
        this.customerName = customerName;
        this.discount = discount;
        this.price = price;
        this.priceWithDiscount = price * (100-discount)/100;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public CarViewModel getCar() {
        return car;
    }

    public void setCar(CarViewModel car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
