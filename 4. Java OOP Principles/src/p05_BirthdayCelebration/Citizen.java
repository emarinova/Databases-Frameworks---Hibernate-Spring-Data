package p05_BirthdayCelebration;

public class Citizen extends BaseResident implements Birthable {
    private int age;
    private String birthdate;

    protected Citizen(String name, int age, String id, String birthdate) {
        super(name, id);
        this.setAge(age);
        this.setBirthdate(birthdate);
    }

    private void setAge(int age) {
        this.age = age;
    }

    private void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String getBirthdate() {
        return this.birthdate;
    }
}