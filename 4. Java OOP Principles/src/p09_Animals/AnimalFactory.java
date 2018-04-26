package p09_Animals;

public class AnimalFactory {
    private AnimalFactory() {

    }

    public static Animal createCat(String name, int age, String gender) {
        return new Cat(name, age, gender);
    }

    public static Animal createDog(String name, int age, String gender) {
        return new Dog(name, age, gender);
    }

    public static Animal createFrog(String name, int age, String gender) {
        return new Frog(name, age, gender);
    }

    public static Animal createKitten(String name, int age) {
        return new Kitten(name, age);
    }

    public static Animal createTomcat(String name, int age) {
        return new Tomcat(name, age);
    }
}
