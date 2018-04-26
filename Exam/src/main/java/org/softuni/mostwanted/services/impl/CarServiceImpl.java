package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.domain.dto.CarJSONImportDto;
import org.softuni.mostwanted.domain.dto.RacerJSONImportDto;
import org.softuni.mostwanted.domain.models.Car;
import org.softuni.mostwanted.domain.models.Racer;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.services.api.CarService;
import org.softuni.mostwanted.services.api.RacerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final RacerService racerService;
    private final ModelParser modelParser;

    public CarServiceImpl(CarRepository carRepository, RacerService racerService, ModelParser modelParser) {
        this.carRepository = carRepository;
        this.racerService = racerService;
        this.modelParser = modelParser;
    }

    @Override
    public void save(CarJSONImportDto dto) {
        Car car = this.modelParser.convert(dto, Car.class);
        Racer racer = this.racerService.findByName(dto.getRacerName());
        if (racer!=null) {
            car.setRacer(racer);
            this.carRepository.saveAndFlush(car);
        } else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public Car findById(Integer id) {
        return this.carRepository.findById(id);
    }
}
