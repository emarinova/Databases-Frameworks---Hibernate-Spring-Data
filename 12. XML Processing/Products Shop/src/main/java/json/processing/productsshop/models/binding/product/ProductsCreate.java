package json.processing.productsshop.models.binding.product;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class ProductsCreate {
    @XmlElement(name = "product")
    private List<ProductCreateBindingModel> products;

    public ProductsCreate() {
        this.products = new ArrayList<>();
    }

    public ProductsCreate(List<ProductCreateBindingModel> products) {
        this.products = products;
    }

    public List<ProductCreateBindingModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductCreateBindingModel> products) {
        this.products = products;
    }
}
