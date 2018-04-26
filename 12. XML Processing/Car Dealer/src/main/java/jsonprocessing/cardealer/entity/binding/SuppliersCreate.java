package jsonprocessing.cardealer.entity.binding;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class SuppliersCreate implements Serializable{
    @XmlElement(name = "supplier")
    private SupplierCreateBindingModel[] suppliers;

    public SuppliersCreate() {
    }

    public SuppliersCreate(SupplierCreateBindingModel[] suppliers) {
        this.suppliers = suppliers;
    }

    public SupplierCreateBindingModel[] getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(SupplierCreateBindingModel[] suppliers) {
        this.suppliers = suppliers;
    }
}
