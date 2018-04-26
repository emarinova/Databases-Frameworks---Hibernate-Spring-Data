package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.domain.dto.DistrictJSONImportDto;
import org.softuni.mostwanted.domain.models.District;
import org.softuni.mostwanted.domain.models.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.services.api.DistrictService;
import org.softuni.mostwanted.services.api.TownService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    private final TownService townService;
    private final ModelParser modelParser;

    public DistrictServiceImpl(DistrictRepository districtRepository, TownService townService, ModelParser modelParser) {
        this.districtRepository = districtRepository;
        this.townService = townService;
        this.modelParser = modelParser;
    }

    @Override
    public void save(DistrictJSONImportDto dto) {
        District district = this.modelParser.convert(dto, District.class);
        Town town = this.townService.findByName(dto.getTownName());
        if(town!=null) {
            district.setTown(town);
        }
        if (this.districtRepository.findByName(dto.getName())!=null){
                List<District> districtsWithThisName = this.districtRepository.findByName(dto.getName());
                for(District distr:districtsWithThisName){
                    if(distr==district){
                        throw new IllegalArgumentException();
                    }
                }
        }
        this.districtRepository.saveAndFlush(district);
        }

    @Override
    public List<District> findByName(String name) {
        return this.districtRepository.findByName(name);
    }
}
