package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "AmmChloride")
public class AmmoniumChloride extends ChemicalIngridient {

    public AmmoniumChloride() {
        super("Ammonium Chloride", BigDecimal.valueOf(9.34), "amCHL");
    }

}
