package json.processing.productsshop.models.view.user;

import com.google.gson.annotations.Expose;
import json.processing.productsshop.models.view.product.ProductBuyerViewModel;

import java.io.Serializable;

public class UserSoldItemsViewModel implements Serializable{
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private ProductBuyerViewModel[] productsSold;

    public UserSoldItemsViewModel() {

    }

    public UserSoldItemsViewModel(String firstName, String lastName, ProductBuyerViewModel[] productsSold) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.productsSold = productsSold;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ProductBuyerViewModel[] getProductsSold() {
        return productsSold;
    }

    public void setProductsSold(ProductBuyerViewModel[] productsSold) {
        this.productsSold = productsSold;
    }
}
