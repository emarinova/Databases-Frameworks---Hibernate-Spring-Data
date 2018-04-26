package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.dto.CarJSONImportDto;
import org.softuni.mostwanted.parser.JSONParser;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.services.api.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CarController {

    private final CarService carService;
    private final JSONParser jsonParser;

    @Autowired
    public CarController(CarService carService, JSONParser jsonParser) {
        this.carService = carService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJson(String jsonContent){
        StringBuilder sb = new StringBuilder();
        try {
            CarJSONImportDto[] cars = this.jsonParser.read(CarJSONImportDto[].class, jsonContent);
            for(CarJSONImportDto car : cars){
                if(ValidationUtil.isValid(car)){
                    try {
                        this.carService.save(car);
                        sb.append(String.format("Successfully imported Car - %s %s @ %d.", car.getBrand(), car.getModel(), car.getYearOfProduction()));
                        sb.append(System.lineSeparator());
                    } catch (IllegalArgumentException iae) {
                        sb.append("Error: Invalid data.");
                        sb.append(System.lineSeparator());
                    }
                } else {
                    sb.append("Error: Invalid data.");
                    sb.append(System.lineSeparator());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
