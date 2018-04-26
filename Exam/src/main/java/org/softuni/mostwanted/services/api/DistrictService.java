package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.domain.dto.DistrictJSONImportDto;
import org.softuni.mostwanted.domain.models.District;

import java.util.List;

public interface DistrictService {
    void save(DistrictJSONImportDto dto);
    List<District> findByName(String name);
}
