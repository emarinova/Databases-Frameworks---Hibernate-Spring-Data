package app.retake.domain.dto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "procedure")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureXMLExportDTO implements Serializable {
    @XmlAttribute(name = "animal-passport")
    private String animalPassport;
    @XmlElement
    private String owner;
    @XmlElementWrapper(name = "animal-aids")
    @XmlElement(name = "animal-aid")
    private List<ProcedureAnimalAidXMLExportDTO> animalAids;

    public ProcedureXMLExportDTO() {
    }

    public String getAnimalPassport() {
        return animalPassport;
    }

    public void setAnimalPassport(String animalPassport) {
        this.animalPassport = animalPassport;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<ProcedureAnimalAidXMLExportDTO> getAnimalAids() {
        return animalAids;
    }

    public void setAnimalAids(List<ProcedureAnimalAidXMLExportDTO> animalAids) {
        this.animalAids = animalAids;
    }
}