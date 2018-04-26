package jsonprocessing.cardealer.entity.binding;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierCreateBindingModel implements Serializable{
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "is-importer")
    private boolean isImporter;

    public SupplierCreateBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
