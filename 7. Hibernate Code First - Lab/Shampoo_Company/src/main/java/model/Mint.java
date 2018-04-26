package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "mint")
public class Mint extends BasicIngredient {
    public Mint() {
        super("mint", BigDecimal.valueOf(2.34));
    }


}
