package jsonprocessing.cardealer.entity.view.Query1;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class CustomersView {
    @XmlElement(name = "customer")
    private List<CustomerOrderedViewModel> customers;

    public CustomersView() {
        this.customers = new ArrayList<>();
    }

    public CustomersView(List<CustomerOrderedViewModel> customers) {
        this.customers = customers;
    }

    public List<CustomerOrderedViewModel> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerOrderedViewModel> customers) {
        this.customers = customers;
    }
}
