package json.processing.productsshop.models.binding.user;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class UsersCreate implements Serializable {
    @XmlElement(name = "user")
    private List<UserCreateBindingModel> users;

    public UsersCreate() {
        this.users = new ArrayList<>();
    }

    public UsersCreate(List<UserCreateBindingModel> users) {
        this.users = users;
    }

    public List<UserCreateBindingModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserCreateBindingModel> users) {
        this.users = users;
    }
}
