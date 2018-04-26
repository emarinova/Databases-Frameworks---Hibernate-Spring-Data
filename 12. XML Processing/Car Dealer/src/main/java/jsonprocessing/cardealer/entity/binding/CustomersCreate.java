package jsonprocessing.cardealer.entity.binding;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class CustomersCreate {
    @XmlElement(name = "customer")
    private CustomerCreateBindingModel[] customers;

    public CustomersCreate() {
    }

    public CustomersCreate(CustomerCreateBindingModel[] customers) {
        this.customers = customers;
    }

    public CustomerCreateBindingModel[] getCustomers() {
        return customers;
    }

    public void setCustomers(CustomerCreateBindingModel[] customers) {
        this.customers = customers;
    }
}
