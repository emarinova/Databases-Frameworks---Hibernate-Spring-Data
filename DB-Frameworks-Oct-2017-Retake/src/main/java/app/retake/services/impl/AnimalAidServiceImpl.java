package app.retake.services.impl;


import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.domain.models.AnimalAid;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalAidRepository;
import app.retake.services.api.AnimalAidService;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class AnimalAidServiceImpl implements AnimalAidService {

    private final AnimalAidRepository animalAidRepository;
    private final ModelParser modelParser;

    public AnimalAidServiceImpl(AnimalAidRepository animalAidRepository, ModelParser modelParser) {
        this.animalAidRepository = animalAidRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(AnimalAidJSONImportDTO dto) {
        AnimalAid animalAid = this.animalAidRepository.findByName(dto.getName());
        if (animalAid!=null) {
            animalAid.setPrice(dto.getPrice());
        } else {
            animalAid = modelParser.convert(dto, AnimalAid.class);
        }
        this.animalAidRepository.saveAndFlush(animalAid);
    }

    @Override
    public AnimalAid findByName(String name) {
        return this.animalAidRepository.findByName(name);
    }
}
