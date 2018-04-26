package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.dto.TownJSONImportDto;
import org.softuni.mostwanted.domain.dto.TownRacersJSONExportDto;
import org.softuni.mostwanted.parser.JSONParser;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.services.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class TownController {
    private final TownService townService;
    private final JSONParser jsonParser;

    @Autowired
    public TownController(TownService townService, JSONParser jsonParser) {
        this.townService = townService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent){
        StringBuilder sb = new StringBuilder();
        try {
            TownJSONImportDto[] towns = this.jsonParser.read(TownJSONImportDto[].class, jsonContent);
            for (TownJSONImportDto town : towns) {
                if (ValidationUtil.isValid(town)) {
                    try {
                        this.townService.save(town);
                        sb.append(String.format("Successfully imported Town - %s.", town.getName()));
                        sb.append(System.lineSeparator());
                    } catch (IllegalArgumentException iae) {
                        sb.append("Error: Duplicate data.");
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

    public String exportDataToJSON() throws IOException, JAXBException {
        List<TownRacersJSONExportDto> towns = this.townService.townRacers();
        String jsonContent = jsonParser.write(towns);
        return jsonContent;
    }
}
