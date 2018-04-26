package org.softuni.mostwanted.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceWrapperXMLImportDto implements Serializable {
    @XmlElement(name = "race")
    private RaceXMLImportDto[] races;

    public RaceWrapperXMLImportDto() {
    }

    public RaceXMLImportDto[] getRaces() {
        return races;
    }

    public void setRaces(RaceXMLImportDto[] races) {
        this.races = races;
    }
}
