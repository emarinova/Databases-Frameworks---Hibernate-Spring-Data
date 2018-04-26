package javadbfundametals.bookshopsystem.entities;

import javadbfundametals.bookshopsystem.enums.AgeRestriction;
import javadbfundametals.bookshopsystem.enums.EditionType;

import java.math.BigDecimal;

public interface ReducedBook {
    public String getTitle();
    public EditionType getEditionType();
    public AgeRestriction getAgeRestriction();
    public BigDecimal getPrice();
}
