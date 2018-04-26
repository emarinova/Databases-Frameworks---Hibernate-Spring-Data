package jsonprocessing.cardealer.entity.view.Query4;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class CarsWithPartsView implements Serializable{
    @XmlElement(name = "car")
    private List<CarWithPartsViewModel> cars;

    public CarsWithPartsView() {
        this.cars = new ArrayList<>();
    }

    public CarsWithPartsView(List<CarWithPartsViewModel> cars) {
        this.cars = cars;
    }

    public List<CarWithPartsViewModel> getCars() {
        return cars;
    }

    public void setCars(List<CarWithPartsViewModel> cars) {
        this.cars = cars;
    }
}
