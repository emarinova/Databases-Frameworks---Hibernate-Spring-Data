package app.retake.services.impl;

import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.domain.models.Vet;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.VetRepository;
import app.retake.services.api.VetService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class VetServiceImpl implements VetService {

    private final VetRepository vetRepository;
    private final ModelParser modelParser;

    public VetServiceImpl(VetRepository vetRepository, ModelParser modelParser) {
        this.vetRepository = vetRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(VetXMLImportDTO dto) {
        Vet vet = this.modelParser.convert(dto, Vet.class);
        this.vetRepository.saveAndFlush(vet);
    }

    @Override
    public Vet findByName(String name) {
        return this.vetRepository.findByName(name);
    }
}
