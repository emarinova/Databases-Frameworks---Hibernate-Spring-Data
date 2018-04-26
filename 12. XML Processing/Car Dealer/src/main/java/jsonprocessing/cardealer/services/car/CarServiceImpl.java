package jsonprocessing.cardealer.services.car;

import jsonprocessing.cardealer.entity.binding.CarCreateBindingModel;
import jsonprocessing.cardealer.entity.model.Car;
import jsonprocessing.cardealer.entity.model.Part;
import jsonprocessing.cardealer.entity.view.Query2.CarViewModel;
import jsonprocessing.cardealer.entity.view.Query4.CarWithPartsViewModel;
import jsonprocessing.cardealer.parser.interfaces.ModelParser;
import jsonprocessing.cardealer.repositories.CarRepository;
import jsonprocessing.cardealer.repositories.PartRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final ModelParser mapper;

    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, ModelParser mapper) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.mapper = mapper;
    }

    @Override
    public void save(CarCreateBindingModel model) {
        Car car = mapper.convert(model, Car.class);
        this.setRandomParts(car);
        this.carRepository.saveAndFlush(car);
    }

    @Override
    public void save(CarCreateBindingModel[] models) {
        for(CarCreateBindingModel model : models){
            this.save(model);
        }
    }

    @Override
    public long count() {
        return this.carRepository.count();
    }

    @Override
    public List<CarViewModel> carsByMake(String make) {
        List<CarViewModel> cars = this.carRepository.findAllByMake(make);
        return cars;
    }

    @Override
    public List<CarWithPartsViewModel> carsWithParts() {
        List<CarWithPartsViewModel> carsWithParts = this.carRepository.carsWithParts();
        for(CarWithPartsViewModel car : carsWithParts){
            car.setParts(this.partRepository.partsForCar(car.getId()));
        }
        return carsWithParts;
    }

    private void setRandomParts(Car car){
        Random random = new Random();
        int partsCount = random.nextInt(11)+10;
        long count = this.partRepository.count();
        for (int i = 0 ; i < partsCount; i++){
            long partId = (long)(random.nextInt((int)count)+1);
            Part part = this.partRepository.findById(partId);
            part.getCars().add(car);
            car.getParts().add(part);
        }
    }
}
