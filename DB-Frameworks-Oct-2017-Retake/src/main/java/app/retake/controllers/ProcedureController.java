package app.retake.controllers;

import app.retake.domain.dto.ProcedureWrapperXMLExportDTO;
import app.retake.domain.dto.ProcedureWrapperXMLImportDTO;
import app.retake.domain.dto.ProcedureXMLImportDTO;
import app.retake.parser.XMLParser;
import app.retake.services.api.ProcedureService;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class ProcedureController {

    private final ProcedureService procedureService;
    private final XMLParser xmlParser;

    public ProcedureController(ProcedureService procedureService, XMLParser xmlParser) {
        this.procedureService = procedureService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromXML(String xmlContent) {
        StringBuilder sb = new StringBuilder();
        try {
            ProcedureWrapperXMLImportDTO procedures = this.xmlParser.read(ProcedureWrapperXMLImportDTO.class, xmlContent);
            for (ProcedureXMLImportDTO procedure : procedures.getProcedures()){
                try {
                    this.procedureService.create(procedure);
                    sb.append("Record successfully imported.");
                    sb.append(System.lineSeparator());
                } catch (IllegalArgumentException e) {
                    sb.append("Error: Invalid data.");
                    sb.append(System.lineSeparator());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String exportProcedures() throws IOException, JAXBException {
        ProcedureWrapperXMLExportDTO procedures = this.procedureService.exportProcedures();
        return this.xmlParser.write(procedures);
    }
}
