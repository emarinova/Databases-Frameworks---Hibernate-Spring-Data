package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.domain.dto.TownJSONImportDto;
import org.softuni.mostwanted.domain.dto.TownRacersJSONExportDto;
import org.softuni.mostwanted.domain.models.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final ModelParser modelParser;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelParser modelParser) {
        this.townRepository = townRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void save(TownJSONImportDto dto) {
        if (this.townRepository.findByName(dto.getName()) == null) {
            Town town = this.modelParser.convert(dto, Town.class);
            this.townRepository.saveAndFlush(town);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public List<TownRacersJSONExportDto> townRacers(){
        return this.townRepository.townRacers();
    }

    @Override
    public Town findByName(String townName) {
        return this.townRepository.findByName(townName);
    }
}
