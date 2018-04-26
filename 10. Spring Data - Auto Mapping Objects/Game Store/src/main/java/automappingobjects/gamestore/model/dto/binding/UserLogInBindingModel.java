package automappingobjects.gamestore.model.dto.binding;

public class UserLogInBindingModel {
    private String email;
    private String password;

    public UserLogInBindingModel() {
    }

    public UserLogInBindingModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
