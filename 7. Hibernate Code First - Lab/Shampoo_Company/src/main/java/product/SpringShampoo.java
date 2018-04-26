package product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "spring")
public class SpringShampoo extends BasicShampoo {

    private static final String NAME = "Spring Shampoo";
    private static final BigDecimal PRICE = BigDecimal.valueOf(3.44);


    public SpringShampoo() {
        super(NAME, PRICE);
    }

}
