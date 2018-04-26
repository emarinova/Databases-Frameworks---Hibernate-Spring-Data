package jsonprocessing.cardealer.entity.view.Query2;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class CarsView {
    @XmlElement(name = "car")
    private List<CarViewModel> cars;

    public CarsView() {
        this.cars = new ArrayList<>();
    }

    public CarsView(List<CarViewModel> cars) {
        this.cars = cars;
    }

    public List<CarViewModel> getCars() {
        return cars;
    }

    public void setCars(List<CarViewModel> cars) {
        this.cars = cars;
    }
}
