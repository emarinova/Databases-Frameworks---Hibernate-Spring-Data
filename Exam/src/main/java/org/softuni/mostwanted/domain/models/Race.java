package org.softuni.mostwanted.domain.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "races")
public class Race implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private int laps;
    @ManyToOne(targetEntity = District.class)
    @JoinColumn(name = "district_id", referencedColumnName = "id", nullable = false)
    private District district;
    @OneToMany(targetEntity = RaceEntry.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "race")
    private Set<RaceEntry> entries;

    public Race() {
        this.entries = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Set<RaceEntry> getEntries() {
        return entries;
    }

    public void setEntries(Set<RaceEntry> entries) {
        this.entries = entries;
    }
}
