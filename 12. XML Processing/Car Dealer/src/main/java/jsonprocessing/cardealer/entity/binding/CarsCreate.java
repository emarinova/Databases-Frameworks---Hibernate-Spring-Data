package jsonprocessing.cardealer.entity.binding;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class CarsCreate {
    @XmlElement(name = "car")
    private CarCreateBindingModel[] cars;

    public CarsCreate() {
    }

    public CarCreateBindingModel[] getCars() {
        return cars;
    }

    public void setCars(CarCreateBindingModel[] cars) {
        this.cars = cars;
    }

    public CarsCreate(CarCreateBindingModel[] cars) {
        this.cars = cars;
    }
}
