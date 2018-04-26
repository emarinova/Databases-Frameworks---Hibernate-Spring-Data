package jsonprocessing.cardealer.entity.view.Query4;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CarWithPartsViewModel  implements Serializable{
    private long id;
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private long travelledDistance;
    @Expose
    private Set<PartViewModel> parts;

    public CarWithPartsViewModel(Long id, String make, String model, long travelledDistance) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
        this.parts = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Set<PartViewModel> getParts() {
        return parts;
    }

    public void setParts(Set<PartViewModel> parts) {
        this.parts = parts;
    }
}
