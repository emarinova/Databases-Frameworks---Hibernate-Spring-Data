package json.processing.productsshop.models.view.product;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class ProductsSold implements Serializable{

    @Expose
    private int count;
    @Expose
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
