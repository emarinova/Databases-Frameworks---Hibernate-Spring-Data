package json.processing.productsshop.models.binding.user;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class UserCreateBindingModel implements Serializable{
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private int age;

    public UserCreateBindingModel() {
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
}
