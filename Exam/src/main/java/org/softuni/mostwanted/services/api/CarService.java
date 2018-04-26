package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.domain.dto.CarJSONImportDto;
import org.softuni.mostwanted.domain.models.Car;

public interface CarService {
    void save(CarJSONImportDto dto);
    Car findById(Integer id);
}
