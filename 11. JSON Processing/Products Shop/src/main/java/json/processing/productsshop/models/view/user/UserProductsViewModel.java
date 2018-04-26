package json.processing.productsshop.models.view.user;

import com.google.gson.annotations.Expose;
import json.processing.productsshop.models.view.product.ProductsSold;

import java.io.Serializable;

public class UserProductsViewModel implements Serializable{
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private int age;
    @Expose
    private ProductsSold soldProducts;

    public UserProductsViewModel() {
    }

    public UserProductsViewModel(String firstName, String lastName, int age, ProductsSold soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.soldProducts = soldProducts;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ProductsSold getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(ProductsSold soldProducts) {
        this.soldProducts = soldProducts;
    }
}
