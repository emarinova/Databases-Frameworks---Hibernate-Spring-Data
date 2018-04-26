package json.processing.productsshop.models.binding.product;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class ProductCreateBindingModel implements Serializable {
    @Expose
    private String name;
    @Expose
    private Double price;
    private long buyer_id;
    private long seller_id;

    public ProductCreateBindingModel() {
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

    public long getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(long buyer_id) {
        this.buyer_id = buyer_id;
    }

    public long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(long seller_id) {
        this.seller_id = seller_id;
    }
}
