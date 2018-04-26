package jsonprocessing.cardealer.entity.view.Query4;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class PartViewModel implements Serializable {
    @Expose
    private String name;
    @Expose
    private double price;

    public PartViewModel(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
