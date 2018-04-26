package p05_BorderControl;

public class Citizen extends BaseResident {
    private int age;

    protected Citizen(String name, int age, String id) {
        super(name, id);
        this.setAge(age);
    }

    public void setAge(int age) {
        this.age = age;
    }
}
