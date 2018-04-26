package app.retake.domain.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "animals")
public class Animal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String type;
    private Integer age;
    @OneToOne(targetEntity = Passport.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "passport_serial_number", referencedColumnName = "serialNumber")
    private Passport passport;
    @OneToMany(mappedBy = "animal", targetEntity = Procedure.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Procedure> procedures;

    public Animal() {
        this.procedures = new HashSet<>();
    }

    public Animal(String name, String type, Integer age, Passport passport) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.passport = passport;
        this.procedures = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public Set<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(Set<Procedure> procedures) {
        this.procedures = procedures;
    }
}
