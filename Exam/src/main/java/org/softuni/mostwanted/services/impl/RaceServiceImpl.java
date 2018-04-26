package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.domain.dto.RaceRaceentryXMLImportDto;
import org.softuni.mostwanted.domain.dto.RaceXMLImportDto;
import org.softuni.mostwanted.domain.models.District;
import org.softuni.mostwanted.domain.models.Race;
import org.softuni.mostwanted.domain.models.RaceEntry;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RaceRepository;
import org.softuni.mostwanted.services.api.DistrictService;
import org.softuni.mostwanted.services.api.RaceEntryService;
import org.softuni.mostwanted.services.api.RaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class RaceServiceImpl implements RaceService {
    private final RaceRepository raceRepository;
    private final DistrictService districtService;
    private final RaceEntryService raceEntryService;
    private final ModelParser modelParser;

    public RaceServiceImpl(RaceRepository raceRepository, DistrictService districtService, RaceEntryService raceEntryService, ModelParser modelParser) {
        this.raceRepository = raceRepository;
        this.districtService = districtService;
        this.raceEntryService = raceEntryService;
        this.modelParser = modelParser;
    }

    @Override
    public Integer save(RaceXMLImportDto dto) {
        Race race = this.modelParser.convert(dto, Race.class);
        District district = this.districtService.findByName(dto.getDistrictName()).get(0);
        if(district!= null){
            race.setDistrict(district);
        } else {
            throw new IllegalArgumentException();
        }
        Set<RaceEntry> raceEntries = new HashSet<>();
        for(RaceRaceentryXMLImportDto raceEntriesDto:dto.getRaceEntries()){
            RaceEntry raceEntry = this.raceEntryService.findById(raceEntriesDto.getId());
            if (raceEntry!=null) {
                raceEntries.add(raceEntry);
            } else {
                throw new IllegalArgumentException();
            }
        }
        for(RaceEntry raceEntry:raceEntries){
            if (raceEntry.getRace()!=null) {
                throw new IllegalArgumentException();
            }
            raceEntry.setRace(race);
        }
        race.setEntries(raceEntries);
        this.raceRepository.saveAndFlush(race);
        return race.getId();
    }
}
