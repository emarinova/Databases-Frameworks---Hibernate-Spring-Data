package jsonprocessing.cardealer.entity.binding;

import java.io.Serializable;

public class SaleCreateBindingModel implements Serializable{
    private Long car;
    private Long customer;
    private int discount;

    public SaleCreateBindingModel() {
    }

    public SaleCreateBindingModel(Long car, Long customer, int discount) {
        this.car = car;
        this.customer = customer;
        this.discount = discount;
    }

    public Long getCar() {
        return car;
    }

    public void setCar(Long car) {
        this.car = car;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
