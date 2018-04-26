package app.retake.domain.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "procedures")
public class Procedure implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany(mappedBy = "procedures")
    private Set<AnimalAid> animalAids;
    @ManyToOne(targetEntity = Animal.class)
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animal animal;
    @Transient
    private Double cost;
    @ManyToOne(targetEntity = Vet.class)
    @JoinColumn(name = "vet_id", referencedColumnName = "id")
    private Vet vet;
    @Column(name = "date_performed")
    private Date date;

    public Procedure() {
        this.animalAids = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<AnimalAid> getAnimalAids() {
        return animalAids;
    }

    public void setAnimalAids(Set<AnimalAid> animalAids) {
        this.animalAids = animalAids;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Double getCost() {
        Double cost = 0.0;
        for (AnimalAid animalAid : this.animalAids){
            cost += animalAid.getPrice();
        }
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
