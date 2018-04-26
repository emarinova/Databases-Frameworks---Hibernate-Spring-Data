package app.retake.controllers;

import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.io.api.FileIO;
import app.retake.parser.JSONParser;
import app.retake.parser.ValidationUtil;
import app.retake.services.api.AnimalAidService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@Component
@Transactional
public class AnimalAidController {

    private final AnimalAidService animalAidService;
    private final JSONParser jsonParser;

    public AnimalAidController(AnimalAidService animalAidService, JSONParser jsonParser) {
        this.animalAidService = animalAidService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
            try {
                AnimalAidJSONImportDTO[] animalAids = this.jsonParser.read(AnimalAidJSONImportDTO[].class, jsonContent);
                for (AnimalAidJSONImportDTO dto : animalAids) {
                    if(ValidationUtil.isValid(dto)) {
                        this.animalAidService.create(dto);
                        sb.append(String.format("Record %s successfully imported.", dto.getName()));
                        sb.append(System.lineSeparator());
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
