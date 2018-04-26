package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.dto.RaceEntryWrapperXMLImportDto;
import org.softuni.mostwanted.domain.dto.RaceEntryXMLImportDto;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.XMLParser;
import org.softuni.mostwanted.services.api.RaceEntryService;
import org.springframework.stereotype.Controller;

import javax.persistence.criteria.CriteriaBuilder;

@Controller
public class RaceEntryController {
    private final RaceEntryService raceEntryService;
    private final XMLParser xmlParser;

    public RaceEntryController(RaceEntryService raceEntryService, XMLParser xmlParser) {
        this.raceEntryService = raceEntryService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String xmlContent){
        StringBuilder sb = new StringBuilder();
        try {
            RaceEntryWrapperXMLImportDto raceEntries = this.xmlParser.read(RaceEntryWrapperXMLImportDto.class, xmlContent);
            for(RaceEntryXMLImportDto raceEntry: raceEntries.getRaceEntries()){
                if(ValidationUtil.isValid(raceEntries)){
                    try {
                        Integer id = this.raceEntryService.save(raceEntry);
                        sb.append(String.format("Successfully imported RaceEntry - %d.", id));
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
