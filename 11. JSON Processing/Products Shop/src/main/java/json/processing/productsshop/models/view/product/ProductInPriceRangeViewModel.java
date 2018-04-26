package json.processing.productsshop.models.view.product;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class ProductInPriceRangeViewModel implements Serializable{
    @Expose
    private String name;
    @Expose
    private Double price;
    private String buyer;
    @Expose
    private String seller;

    public ProductInPriceRangeViewModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
