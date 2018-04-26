package json.processing.productsshop.models.view.user.Query4;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class UsersProducts implements Serializable{
    @XmlAttribute(name = "count")
    private int count;
    @XmlElement(name = "user")
    private List<UserProductsViewModel> users;

    public UsersProducts() {
        this.users = new ArrayList<>();
    }

    public UsersProducts(int count, List<UserProductsViewModel> users) {
        this.count = count;
        this.users = users;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UserProductsViewModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserProductsViewModel> users) {
        this.users = users;
    }
}
