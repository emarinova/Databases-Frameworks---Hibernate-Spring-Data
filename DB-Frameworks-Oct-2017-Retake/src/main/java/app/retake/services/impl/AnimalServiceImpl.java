package app.retake.services.impl;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import app.retake.domain.models.Passport;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalRepository;
import app.retake.repositories.PassportRepository;
import app.retake.services.api.AnimalService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository animalRepository;
    private final PassportRepository passportRepository;
    private final ModelParser modelParser;

    public AnimalServiceImpl(AnimalRepository animalRepository, PassportRepository passportRepository, ModelParser modelParser) {
        this.animalRepository = animalRepository;
        this.passportRepository = passportRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(AnimalJSONImportDTO dto) {
       Animal animal = this.modelParser.convert(dto, Animal.class);
       Passport passport = this.modelParser.convert(dto.getPassport(), Passport.class);
       if (this.passportRepository.findBySerialNumber(passport.getSerialNumber())==null) {
           animal.setPassport(passport);
           this.animalRepository.saveAndFlush(animal);
       } else {
           throw new IllegalArgumentException();
       }
    }

    @Override
    public List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber) {
        return this.animalRepository.allAnimalsByOwnerPhoneNumber(phoneNumber);
    }

    @Override
    public Animal findByPassportSerialNumber(String passport) {
        return this.animalRepository.findByPassportSerialNumber(passport);
    }
}
