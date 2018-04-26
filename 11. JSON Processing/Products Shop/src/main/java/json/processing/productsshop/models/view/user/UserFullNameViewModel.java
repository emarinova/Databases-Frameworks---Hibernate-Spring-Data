package json.processing.productsshop.models.view.user;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class UserFullNameViewModel implements Serializable{
    private Long id;
    private String firstName;
    private String lastName;
    @Expose
    private String fullName;

    public UserFullNameViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setFullName() {
        this.fullName = this.getFirstName() + " " + this.getLastName();
    }

    public String getFullName() {
        if (this.getFirstName() == null) {
            return this.getLastName();
        } else {
            return this.getFirstName() + " " + this.getLastName();
        }
    }


}
