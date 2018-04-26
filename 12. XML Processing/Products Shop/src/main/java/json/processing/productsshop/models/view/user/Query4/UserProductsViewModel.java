package json.processing.productsshop.models.view.user.Query4;

import json.processing.productsshop.models.view.product.Query4.ProductsSold;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserProductsViewModel implements Serializable{
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlAttribute(name = "age")
    private int age;
    @XmlElement(name = "sold-products")
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
