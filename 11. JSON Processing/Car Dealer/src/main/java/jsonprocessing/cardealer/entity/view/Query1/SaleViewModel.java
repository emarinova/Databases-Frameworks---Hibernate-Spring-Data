package jsonprocessing.cardealer.entity.view.Query1;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class SaleViewModel implements Serializable {
    @Expose
    private String carModel;

    public SaleViewModel() {
    }

    public SaleViewModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}
