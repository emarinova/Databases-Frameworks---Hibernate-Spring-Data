package org.softuni.mostwanted.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryWrapperXMLImportDto implements Serializable {
    @XmlElement(name = "race-entry")
    private RaceEntryXMLImportDto[] raceEntries;

    public RaceEntryWrapperXMLImportDto() {
    }

    public RaceEntryXMLImportDto[] getRaceEntries() {
        return raceEntries;
    }

    public void setRaceEntries(RaceEntryXMLImportDto[] raceEntries) {
        this.raceEntries = raceEntries;
    }
}
