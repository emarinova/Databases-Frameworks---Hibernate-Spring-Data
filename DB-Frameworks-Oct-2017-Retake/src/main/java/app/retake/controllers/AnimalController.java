package app.retake.controllers;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.parser.JSONParser;
import app.retake.parser.ValidationUtil;
import app.retake.services.api.AnimalService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class AnimalController {

    private final AnimalService animalService;
    private final JSONParser jsonParser;

    public AnimalController(AnimalService animalService, JSONParser jsonParser) {
        this.animalService = animalService;
        this.jsonParser = jsonParser;
    }


    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            AnimalJSONImportDTO[] animals = this.jsonParser.read(AnimalJSONImportDTO[].class, jsonContent);
            for (AnimalJSONImportDTO animal : animals){
                if (ValidationUtil.isValid(animal) && ValidationUtil.isValid(animal.getPassport())){
                    try {
                        this.animalService.create(animal);
                        sb.append(String.format("Record %s Passport №: %s} successfully imported.", animal.getName(), animal.getPassport().getSerialNumber()));
                        sb.append(System.lineSeparator());
                    } catch (IllegalArgumentException e) {
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

    public String exportAnimalsByOwnerPhoneNumber(String phoneNumber) {
        String jsonAnimals = "";
        try {
            jsonAnimals = jsonParser.write(this.animalService.findByOwnerPhoneNumber(phoneNumber));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonAnimals;
    }
}
