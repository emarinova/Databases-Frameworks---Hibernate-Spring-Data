package json.processing.productsshop.models.view.product.Query1;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class ProductInRange implements Serializable {
    @XmlElement(name = "product")
    private List<ProductInPriceRangeViewModel> products;

    public ProductInRange() {
        this.products = new ArrayList<>();
    }

    public ProductInRange(List<ProductInPriceRangeViewModel> products) {
        this.products = products;
    }

    public List<ProductInPriceRangeViewModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInPriceRangeViewModel> products) {
        this.products = products;
    }
}
