package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.dto.RaceEntryWrapperXMLImportDto;
import org.softuni.mostwanted.domain.dto.RaceEntryXMLImportDto;
import org.softuni.mostwanted.domain.dto.RaceWrapperXMLImportDto;
import org.softuni.mostwanted.domain.dto.RaceXMLImportDto;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.XMLParser;
import org.softuni.mostwanted.services.api.RaceService;
import org.springframework.stereotype.Controller;

@Controller
public class RaceController {
    private final RaceService raceService;
    private final XMLParser xmlParser;

    public RaceController(RaceService raceService, XMLParser xmlParser) {
        this.raceService = raceService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String xmlContent){
        StringBuilder sb = new StringBuilder();
        try {
            RaceWrapperXMLImportDto races = this.xmlParser.read(RaceWrapperXMLImportDto.class, xmlContent);
            for(RaceXMLImportDto race: races.getRaces()){
                if(ValidationUtil.isValid(race)){
                    try {
                        Integer id = this.raceService.save(race);
                        sb.append(String.format("Successfully imported RaceEntry - %d.", id));
                        sb.append(System.lineSeparator());
                    } catch (IllegalArgumentException iae){
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
