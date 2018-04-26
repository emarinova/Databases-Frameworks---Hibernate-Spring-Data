package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.domain.dto.RaceEntryXMLImportDto;
import org.softuni.mostwanted.domain.models.Car;
import org.softuni.mostwanted.domain.models.RaceEntry;
import org.softuni.mostwanted.domain.models.Racer;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.services.api.CarService;
import org.softuni.mostwanted.services.api.RaceEntryService;
import org.softuni.mostwanted.services.api.RacerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RaceEntryServiceImpl implements RaceEntryService {
    private final RaceEntryRepository raceEntryRepository;
    private final CarService carService;
    private final RacerService racerService;
    private final ModelParser modelParser;

    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository, CarService carService, RacerService racerService, ModelParser modelParser) {
        this.raceEntryRepository = raceEntryRepository;
        this.carService = carService;
        this.racerService = racerService;
        this.modelParser = modelParser;
    }

    @Override
    public Integer save(RaceEntryXMLImportDto dto) {
        RaceEntry raceEntry = this.modelParser.convert(dto, RaceEntry.class);
        Car car = this.carService.findById(dto.getCar());
        Racer racer = this.racerService.findByName(dto.getRacer());
        if (racer==null || car==null) {
            throw new IllegalArgumentException();
        }
        raceEntry.setCar(car);
        raceEntry.setRacer(racer);
        this.raceEntryRepository.saveAndFlush(raceEntry);
        return raceEntry.getId();
    }

    @Override
    public RaceEntry findById(Integer id) {
        return this.raceEntryRepository.findById(id);
    }

    @Override
    public List<RaceEntry> findByRacer(String name) {
        return this.raceEntryRepository.findDistinctByRacerName(name);
    }
}
