package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "nettle")
public class Nettle extends BasicIngredient {
    public Nettle() {
        super("nettle", BigDecimal.valueOf(3.45));
    }
}
