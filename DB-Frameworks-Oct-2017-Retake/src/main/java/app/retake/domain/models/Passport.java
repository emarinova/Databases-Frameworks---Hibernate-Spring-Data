package app.retake.domain.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "passports")
public class Passport implements Serializable {
    @Id
    private String serialNumber;
    @OneToOne(mappedBy = "passport", targetEntity = Animal.class)
    private Animal animal;
    @NotNull
    private String ownerPhoneNumber;
    private String ownerName;
    @Column(name = "registered_on")
    private Date registrationDate;

    public Passport() {
    }

    public Passport(String serialNumber, String ownerPhoneNumber, String ownerName, Date registrationDate) {
        this.serialNumber = serialNumber;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.ownerName = ownerName;
        this.registrationDate = registrationDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
