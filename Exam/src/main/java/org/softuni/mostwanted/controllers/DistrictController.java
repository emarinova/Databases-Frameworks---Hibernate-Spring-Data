package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.domain.dto.DistrictJSONImportDto;
import org.softuni.mostwanted.domain.dto.TownJSONImportDto;
import org.softuni.mostwanted.parser.JSONParser;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.services.api.DistrictService;
import org.springframework.stereotype.Controller;

@Controller
public class DistrictController {
    private final DistrictService districtService;
    private final JSONParser jsonParser;

    public DistrictController(DistrictService districtService, JSONParser jsonParser) {
        this.districtService = districtService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent){
        StringBuilder sb = new StringBuilder();
        try {
            DistrictJSONImportDto[] districts = this.jsonParser.read(DistrictJSONImportDto[].class, jsonContent);
            for (DistrictJSONImportDto district: districts) {
                if (ValidationUtil.isValid(district)) {
                    try {
                        this.districtService.save(district);
                        sb.append(String.format("Successfully imported District - %s.", district.getName()));
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
}
