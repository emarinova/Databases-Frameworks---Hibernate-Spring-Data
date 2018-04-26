package jsonprocessing.cardealer.entity.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Supplier {
    private Long id;
    private String name;
    private boolean isImporter;
    private Set<Part> parts;

    public Supplier() {
        this.parts = new HashSet<>();
    }

    public Supplier(Long id, String name, boolean isImporter, Set<Part> parts) {
        this.id = id;
        this.name = name;
        this.isImporter = isImporter;
        this.parts = parts;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }

    @OneToMany(mappedBy = "supplier")
    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }
}
