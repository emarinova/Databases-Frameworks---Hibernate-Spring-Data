package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.domain.dto.*;
import org.softuni.mostwanted.domain.models.Car;
import org.softuni.mostwanted.domain.models.RaceEntry;
import org.softuni.mostwanted.domain.models.Racer;
import org.softuni.mostwanted.domain.models.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.services.api.RaceEntryService;
import org.softuni.mostwanted.services.api.RacerService;
import org.softuni.mostwanted.services.api.TownService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RacerServiceImpl implements RacerService {
    private final RacerRepository racerRepository;
    private final TownService townService;
    private final RaceEntryService raceEntryService;
    private final ModelParser modelParser;

    public RacerServiceImpl(RacerRepository racerRepository, TownService townService, @Lazy RaceEntryService raceEntryService, ModelParser modelParser) {
        this.racerRepository = racerRepository;
        this.townService = townService;
        this.raceEntryService = raceEntryService;
        this.modelParser = modelParser;
    }

    @Override
    public void save(RacerJSONImportDto dto) {
        Racer racer = this.modelParser.convert(dto, Racer.class);
        Town town = this.townService.findByName(dto.getHomeTown());
        if(town==null){
            this.townService.save(new TownJSONImportDto(dto.getHomeTown()));
            town = this.townService.findByName(dto.getHomeTown());
        }
        racer.setHomeTown(town);
        this.racerRepository.saveAndFlush(racer);
    }

    @Override
    public Racer findByName(String name) {
        return this.racerRepository.findByName(name);
    }

    @Override
    public List<RacerCarsJSONExportDto> racerCars() {
        List<Racer> racers = this.racerRepository.findDistinctByCarsNotNull();
        List<RacerCarsJSONExportDto> racersCars = new ArrayList<>();
        for(Racer racer : racers){
            List<String> carsString = new ArrayList<>();
            for(Car car : racer.getCars()){
                String carString = car.getBrand() + " " + car.getModel() + " " + car.getYearOfProduction();
                carsString.add(carString);
            }
            RacerCarsJSONExportDto racerCars = new RacerCarsJSONExportDto(racer.getName(), racer.getAge(), carsString);
            racersCars.add(racerCars);
        }
        Comparator<RacerCarsJSONExportDto> compBySize = Comparator.comparingInt(x -> x.getCars().size());
        Comparator<RacerCarsJSONExportDto> compByName = Comparator.comparing(x -> x.getName());
        racersCars = racersCars.stream().sorted(compBySize.reversed().thenComparing(compByName)).collect(Collectors.toList());
        return racersCars;
    }

    @Override
    public MostWantedXMLExportDto mostWanted() {
        List<RacerXMLExportDto> racers = this.racerRepository.racersWithEntries();
        MostWantedXMLExportDto mostWanted = new MostWantedXMLExportDto();
        mostWanted.setRacer(racers.get(0));
        List<RaceEntryXMLExportDto> raceEntriesDto = new ArrayList<>();
        List<RaceEntry> raceEntries = this.raceEntryService.findByRacer(mostWanted.getRacer().getName());
        for(RaceEntry raceEntry : raceEntries){
            RaceEntryXMLExportDto raceEntryDto = modelParser.convert(raceEntry, RaceEntryXMLExportDto.class);
            raceEntryDto.setCar(String.format("%s %s @ %d", raceEntry.getCar().getBrand(), raceEntry.getCar().getModel(),raceEntry.getCar().getYearOfProduction()));
            raceEntriesDto.add(raceEntryDto);
        }
        raceEntriesDto = raceEntriesDto.stream().sorted(Comparator.comparing(x -> x.getFinishTime())).collect(Collectors.toList());
        mostWanted.getRacer().setEntries(raceEntriesDto);
        return mostWanted;
    }
}
