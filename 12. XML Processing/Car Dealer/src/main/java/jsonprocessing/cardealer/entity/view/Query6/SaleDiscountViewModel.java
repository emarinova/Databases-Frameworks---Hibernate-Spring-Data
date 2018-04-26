package jsonprocessing.cardealer.entity.view.Query6;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Set;

@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleDiscountViewModel implements Serializable {
    @XmlTransient
    private long carId;
    @XmlElement(name = "car")
    private CarViewModel car;
    @XmlElement(name = "customer-name")
    private String customerName;
    @XmlElement(name = "discount")
    private double discount;
    @XmlElement(name = "price")
    private double price;
    @XmlElement(name = "price-with-discount")
    private double priceWithDiscount;

    public SaleDiscountViewModel() {
    }

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
