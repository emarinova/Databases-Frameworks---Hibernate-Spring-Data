package app.retake.controllers;

import app.retake.domain.dto.VetWrapperXMLImportDTO;
import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.parser.ValidationUtil;
import app.retake.parser.XMLParser;
import app.retake.services.api.VetService;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class VetController {
    private final VetService vetService;
    private final XMLParser xmlParser;

    public VetController(VetService vetService, XMLParser xmlParser) {
        this.vetService = vetService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String xmlContent)  {
        StringBuilder sb = new StringBuilder();
        try{
            VetWrapperXMLImportDTO vets = this.xmlParser.read(VetWrapperXMLImportDTO.class, xmlContent);
            for (VetXMLImportDTO vet : vets.getVets()){
                if(ValidationUtil.isValid(vet)){
                    this.vetService.create(vet);
                    sb.append(String.format("Record %s successfully imported.", vet.getName()));
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
