package p09_Animals;

public class Kitten extends Cat {
    private static final String DEFAULT_KITTEN_GENDER = "Female";

    public Kitten(String name, int age) {
        super(name, age, DEFAULT_KITTEN_GENDER);
    }

    @Override
    public String produceSound() {
        return "Miau";
    }
}
