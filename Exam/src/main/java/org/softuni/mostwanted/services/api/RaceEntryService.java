package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.domain.dto.RaceEntryWrapperXMLImportDto;
import org.softuni.mostwanted.domain.dto.RaceEntryXMLImportDto;
import org.softuni.mostwanted.domain.models.RaceEntry;

import java.util.List;

public interface RaceEntryService {
    Integer save(RaceEntryXMLImportDto dto);
    RaceEntry findById(Integer id);
    List<RaceEntry> findByRacer(String name);
}
