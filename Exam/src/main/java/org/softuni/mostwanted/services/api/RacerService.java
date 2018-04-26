package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.domain.dto.MostWantedXMLExportDto;
import org.softuni.mostwanted.domain.dto.RacerCarsJSONExportDto;
import org.softuni.mostwanted.domain.dto.RacerJSONImportDto;
import org.softuni.mostwanted.domain.models.Racer;

import java.util.List;

public interface RacerService {
    void save(RacerJSONImportDto dto);
    Racer findByName(String name);
    List<RacerCarsJSONExportDto> racerCars();
    MostWantedXMLExportDto mostWanted();
}
