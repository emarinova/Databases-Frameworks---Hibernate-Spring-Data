package p01_DefineAnInterfacePerson;

class Citizen implements Person {
    private String name;
    private int age;

    public Citizen() {
    }

    public Citizen(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}