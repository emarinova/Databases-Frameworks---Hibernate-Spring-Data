package json.processing.productsshop.models.view.user.Query2;

import json.processing.productsshop.models.view.product.Query2.ProductsSoldWIthBuyers;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class UserSoldItemsViewModel implements Serializable{
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlElement(name = "sold-products")
    private ProductsSoldWIthBuyers productsSold;

    public UserSoldItemsViewModel() {

    }

    public UserSoldItemsViewModel(String firstName, String lastName, ProductsSoldWIthBuyers productsSold) {
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

    public ProductsSoldWIthBuyers getProductsSold() {
        return productsSold;
    }

    public void setProductsSold(ProductsSoldWIthBuyers productsSold) {
        this.productsSold = productsSold;
    }
}
