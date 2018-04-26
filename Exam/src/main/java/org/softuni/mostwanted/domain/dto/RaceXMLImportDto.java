package org.softuni.mostwanted.domain.dto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceXMLImportDto implements Serializable {
    @XmlElement
    private int laps;
    @XmlElement(name = "district-name")
    private String districtName;
    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private RaceRaceentryXMLImportDto[] raceEntries;

    public RaceXMLImportDto() {
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public RaceRaceentryXMLImportDto[] getRaceEntries() {
        return raceEntries;
    }

    public void setRaceEntries(RaceRaceentryXMLImportDto[] raceEntries) {
        this.raceEntries = raceEntries;
    }
}
