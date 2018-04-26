package jsonprocessing.cardealer.entity.view.Query3;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class SuppliersView implements Serializable{
    @XmlElement(name = "supplier")
    private List<SupplierLocalViewModel> suppliers;

    public SuppliersView() {
        this.suppliers = new ArrayList<>();
    }

    public List<SupplierLocalViewModel> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierLocalViewModel> suppliers) {
        this.suppliers = suppliers;
    }
}
