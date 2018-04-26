package app.retake.domain.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class AnimalAidJSONImportDTO implements Serializable {
    @Size(min = 3)
    @Expose
    private String name;
    @DecimalMin("0.01")
    @Expose
    private Double price;

    public AnimalAidJSONImportDTO() {
    }

    public AnimalAidJSONImportDTO(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
