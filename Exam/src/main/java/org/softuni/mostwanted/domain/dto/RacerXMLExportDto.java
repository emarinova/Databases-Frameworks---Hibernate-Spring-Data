package org.softuni.mostwanted.domain.dto;

import javax.xml.bind.annotation.*;
import java.beans.Transient;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "racer")
@XmlAccessorType(XmlAccessType.FIELD)
public class RacerXMLExportDto implements Serializable {
    @XmlAttribute
    private String name;
    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<RaceEntryXMLExportDto> entries;

    public RacerXMLExportDto() {
    }

    public RacerXMLExportDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RaceEntryXMLExportDto> getEntries() {
        return entries;
    }

    public void setEntries(List<RaceEntryXMLExportDto> entries) {
        this.entries = entries;
    }
}
