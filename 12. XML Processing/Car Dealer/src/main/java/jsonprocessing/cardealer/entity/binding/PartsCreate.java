package jsonprocessing.cardealer.entity.binding;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class PartsCreate implements Serializable {
    @XmlElement(name = "part")
    private PartCreateBindingModel[] parts;

    public PartsCreate() {
    }

    public PartsCreate(PartCreateBindingModel[] parts) {
        this.parts = parts;
    }

    public PartCreateBindingModel[] getParts() {
        return parts;
    }

    public void setParts(PartCreateBindingModel[] parts) {
        this.parts = parts;
    }
}
