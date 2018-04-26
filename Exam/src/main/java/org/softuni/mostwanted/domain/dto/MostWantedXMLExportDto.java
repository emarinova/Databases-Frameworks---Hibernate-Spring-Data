package org.softuni.mostwanted.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "most-wanted")
@XmlAccessorType(XmlAccessType.FIELD)
public class MostWantedXMLExportDto implements Serializable {
    @XmlElement
    RacerXMLExportDto racer;

    public MostWantedXMLExportDto() {
    }

    public RacerXMLExportDto getRacer() {
        return racer;
    }

    public void setRacer(RacerXMLExportDto racer) {
        this.racer = racer;
    }
}
