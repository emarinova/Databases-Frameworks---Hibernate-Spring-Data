package jsonprocessing.cardealer.entity.view.Query6;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class SalesViewModel implements Serializable{
    @XmlElement(name = "sale")
    private List<SaleDiscountViewModel> sales;

    public SalesViewModel() {
    }

    public SalesViewModel(List<SaleDiscountViewModel> sales) {
        this.sales = sales;
    }

    public List<SaleDiscountViewModel> getSales() {
        return sales;
    }

    public void setSales(List<SaleDiscountViewModel> sales) {
        this.sales = sales;
    }
}
