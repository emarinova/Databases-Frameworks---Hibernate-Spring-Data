package org.softuni.mostwanted.domain.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class RacerCarsJSONExportDto implements Serializable {
    @Expose
    private String name;
    @Expose
    private Integer age;
    @Expose
    private List<String> cars;

    public RacerCarsJSONExportDto() {

    }

    public RacerCarsJSONExportDto(String name, Integer age, List<String> cars) {
        this.name = name;
        this.age = age;
        this.cars = cars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getCars() {
        return cars;
    }

    public void setCars(List<String> cars) {
        this.cars = cars;
    }
}
