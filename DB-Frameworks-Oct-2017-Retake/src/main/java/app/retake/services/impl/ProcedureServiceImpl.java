package app.retake.services.impl;

import app.retake.domain.dto.*;
import app.retake.domain.models.Animal;
import app.retake.domain.models.AnimalAid;
import app.retake.domain.models.Procedure;
import app.retake.domain.models.Vet;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.ProcedureRepository;
import app.retake.services.api.AnimalAidService;
import app.retake.services.api.AnimalService;
import app.retake.services.api.ProcedureService;
import app.retake.services.api.VetService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final ModelParser modelParser;
    private final AnimalService animalService;
    private final VetService vetService;
    private final AnimalAidService animalAidService;

    public ProcedureServiceImpl(ProcedureRepository procedureRepository, ModelParser modelParser, AnimalService animalService, VetService vetService, AnimalAidService animalAidService) {
        this.procedureRepository = procedureRepository;
        this.modelParser = modelParser;
        this.animalService = animalService;
        this.vetService = vetService;
        this.animalAidService = animalAidService;
    }

    @Override
    public void create(ProcedureXMLImportDTO dto) throws ParseException {
        Procedure procedure = new Procedure();
        Animal animal = this.animalService.findByPassportSerialNumber(dto.getAnimal());
        Vet vet = this.vetService.findByName(dto.getVet());
        if (animal == null || vet == null) {
            throw new IllegalArgumentException();
        } else {
            procedure.setAnimal(animal);
            procedure.setVet(vet);
        }
        Set<AnimalAid> aids = new HashSet<>();
        for (ProcedureAnimalAidXMLImportDTO animalAidDto : dto.getAnimalAids()){
            AnimalAid animalAid = this.animalAidService.findByName(animalAidDto.getName());
            if (animalAid==null){
                throw new IllegalArgumentException();
            } else {
                animalAid.getProcedures().add(procedure);
                aids.add(animalAid);
            }
        }
        procedure.setAnimalAids(aids);
        this.procedureRepository.saveAndFlush(procedure);
    }

    @Override
    public ProcedureWrapperXMLExportDTO exportProcedures() {
        List<Procedure> allProcedures = this.procedureRepository.findAll();
        List<ProcedureXMLExportDTO> procedures = new ArrayList<>();
        for (Procedure p : allProcedures){
            ProcedureXMLExportDTO procedure = new ProcedureXMLExportDTO();
            procedure.setAnimalPassport(p.getAnimal().getPassport().getSerialNumber());
            procedure.setOwner(p.getAnimal().getPassport().getOwnerName());
            procedure.setAnimalAids(new ArrayList<>());
            for (AnimalAid animalAid : p.getAnimalAids()){
                procedure.getAnimalAids().add(new ProcedureAnimalAidXMLExportDTO(animalAid.getName()));
            }
            procedures.add(procedure);
        }
        return new ProcedureWrapperXMLExportDTO(procedures);
    }
}

