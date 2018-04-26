package jsonprocessing.cardealer.services.car;

import jsonprocessing.cardealer.entity.binding.CarCreateBindingModel;
import jsonprocessing.cardealer.entity.view.Query2.CarViewModel;
import jsonprocessing.cardealer.entity.view.Query4.CarWithPartsViewModel;

import java.util.List;

public interface CarService {
    void save(CarCreateBindingModel model);
    void save(CarCreateBindingModel[] models);
    long count();
    List<CarViewModel> carsByMake(String make);
    List<CarWithPartsViewModel> carsWithParts();
}
