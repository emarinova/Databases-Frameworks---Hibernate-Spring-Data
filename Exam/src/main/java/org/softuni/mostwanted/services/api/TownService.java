package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.domain.dto.TownJSONImportDto;
import org.softuni.mostwanted.domain.dto.TownRacersJSONExportDto;
import org.softuni.mostwanted.domain.models.Town;

import java.util.List;

public interface TownService {
    void save(TownJSONImportDto dto);
    Town findByName(String townName);
    List<TownRacersJSONExportDto> townRacers();
}
