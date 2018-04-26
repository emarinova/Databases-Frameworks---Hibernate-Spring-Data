package jsonprocessing.cardealer.entity.view.Query5;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSalesViewModel implements Serializable {
    @XmlTransient
    private long id;
    @XmlAttribute(name = "full-name")
    private String fullName;
    @XmlAttribute(name = "bought-cars")
    private long boughtCars;
    @XmlAttribute(name = "spent-money")
    private double spentMoney;

    public CustomerSalesViewModel() {
    }

    public CustomerSalesViewModel(long id, String fullName, long boughtCars) {
        this.id = id;
        this.fullName = fullName;
        this.boughtCars = boughtCars;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(long boughtCars) {
        this.boughtCars = boughtCars;
    }

    public double getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(double spentMoney) {
        this.spentMoney = spentMoney;
    }
}
