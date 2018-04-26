package org.softuni.mostwanted.domain.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "districts")
public class District implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @ManyToOne(targetEntity = Town.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town town;

    public District() {
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

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }
}
