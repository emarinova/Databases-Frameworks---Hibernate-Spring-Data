package org.softuni.mostwanted.domain.dto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "race-entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryXMLImportDto implements Serializable {
    @XmlAttribute(name = "has-finished")
    private Boolean hasFinished;
    @XmlAttribute(name = "finish-time")
    private Double finishTime;
    @XmlAttribute(name = "car-id")
    private Integer car;
    @XmlElement
    private String racer;

    public RaceEntryXMLImportDto() {
    }

    public Boolean getHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(Boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public Double getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getCar() {
        return car;
    }

    public void setCar(Integer car) {
        this.car = car;
    }

    public String getRacer() {
        return racer;
    }

    public void setRacer(String racer) {
        this.racer = racer;
    }
}
