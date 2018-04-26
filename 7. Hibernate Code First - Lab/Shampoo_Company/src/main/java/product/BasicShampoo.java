package product;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="basic_shampoo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BasicShampoo implements Shampoo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Basic
    private String name;

    @OneToOne(optional = false)
    @JoinColumn(name = "label_id", referencedColumnName = "id")
    private ClassicLabel label;

    private BigDecimal price;

    public BasicShampoo() {
    }

    public BasicShampoo(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassicLabel getLabel() {
        return label;
    }

    public void setLabel(ClassicLabel label) {
        this.label = label;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
