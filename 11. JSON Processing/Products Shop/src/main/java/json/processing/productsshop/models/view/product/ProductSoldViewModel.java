package json.processing.productsshop.models.view.product;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductSoldViewModel implements Serializable {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;

    public ProductSoldViewModel() {
    }

    public ProductSoldViewModel(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
