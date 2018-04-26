package json.processing.productsshop.models.view.user.Query2;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class UsersSoldItems implements Serializable{
    @XmlElement(name = "user")
    private List<UserSoldItemsViewModel> users;

    public UsersSoldItems() {
        this.users = new ArrayList<>();
    }

    public UsersSoldItems(List<UserSoldItemsViewModel> users) {
        this.users = users;
    }

    public List<UserSoldItemsViewModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserSoldItemsViewModel> users) {
        this.users = users;
    }
}
