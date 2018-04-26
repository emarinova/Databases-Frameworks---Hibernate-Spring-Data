package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.dto.MostWantedXMLExportDto;
import org.softuni.mostwanted.domain.dto.RacerCarsJSONExportDto;
import org.softuni.mostwanted.domain.dto.RacerJSONImportDto;
import org.softuni.mostwanted.parser.JSONParser;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.XMLParser;
import org.softuni.mostwanted.services.api.RacerService;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class RacerController {
    private final RacerService racerService;
    private final JSONParser jsonParser;
    private final XMLParser xmlParser;

    public RacerController(RacerService racerService, JSONParser jsonParser, XMLParser xmlParser) {
        this.racerService = racerService;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
    }

    public String importDataFromJson(String jsonContent){
        StringBuilder sb = new StringBuilder();
        try {
            RacerJSONImportDto[] racers = this.jsonParser.read(RacerJSONImportDto[].class, jsonContent);
            for(RacerJSONImportDto racer : racers){
                if(ValidationUtil.isValid(racer)){
                    this.racerService.save(racer);
                    sb.append(String.format("Successfully imported Racer - %s.", racer.getName()));
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

    public String exportDataToJSON() throws IOException, JAXBException {
        List<RacerCarsJSONExportDto> racersCars = this.racerService.racerCars();
        return this.jsonParser.write(racersCars);
    }

    public String exportDataToXML() throws IOException, JAXBException {
        MostWantedXMLExportDto mostWanted = this.racerService.mostWanted();
        return this.xmlParser.write(mostWanted);
    }
}
