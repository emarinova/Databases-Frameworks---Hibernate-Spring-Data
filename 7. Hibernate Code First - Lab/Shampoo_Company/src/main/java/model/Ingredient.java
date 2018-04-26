package model;

import java.io.Serializable;
import java.math.BigDecimal;

public interface Ingredient extends Serializable {

    String getName();

    void setName(String name);

    int getId();

    void setId(int id);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

}
