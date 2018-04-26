package jsonprocessing.cardealer.entity.view.Query5;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class CustomersSalesView {
    @XmlElement(name = "customer")
    private List<CustomerSalesViewModel> customers;

    public CustomersSalesView() {
    }

    public CustomersSalesView(List<CustomerSalesViewModel> customers) {
        this.customers = customers;
    }

    public List<CustomerSalesViewModel> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerSalesViewModel> customers) {
        this.customers = customers;
    }
}
