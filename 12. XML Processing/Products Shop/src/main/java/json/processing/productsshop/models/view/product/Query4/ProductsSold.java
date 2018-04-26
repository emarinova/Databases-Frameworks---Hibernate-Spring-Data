package json.processing.productsshop.models.view.product.Query4;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsSold implements Serializable{

    @XmlAttribute(name = "count")
    private int count;
    @XmlElement(name = "product")
    private List<ProductSoldViewModel> products;

    public ProductsSold() {
    }

    public ProductsSold(int count, List<ProductSoldViewModel> products) {
        this.count = count;
        this.products = products;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductSoldViewModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSoldViewModel> products) {
        this.products = products;
    }
}
