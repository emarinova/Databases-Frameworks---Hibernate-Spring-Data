package jsonprocessing.cardealer.entity.view.Query5;

import jsonprocessing.cardealer.entity.model.Car;
import jsonprocessing.cardealer.entity.model.Customer;

public class SalePrice {
    private Long customer;
    private Double moneySpent;

    public SalePrice(Long customer, Double moneySpent) {
        this.customer = customer;
        this.moneySpent = moneySpent;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }


    public Double getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(Double moneySpent) {
        this.moneySpent = moneySpent;
    }
}
