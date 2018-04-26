package json.processing.productsshop.models.view.product.Query2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsSoldWIthBuyers {
    @XmlElement(name = "product")
    private ProductBuyerViewModel[] products;

    public ProductsSoldWIthBuyers() {
    }

    public ProductsSoldWIthBuyers(ProductBuyerViewModel[] products) {
        this.products = products;
    }

    public ProductBuyerViewModel[] getProducts() {
        return products;
    }

    public void setProducts(ProductBuyerViewModel[] products) {
        this.products = products;
    }
}
