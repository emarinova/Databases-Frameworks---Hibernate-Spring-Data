package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public abstract class ChemicalIngridient extends BasicIngredient implements ChemicalIngredients {

    @Column(name="chemical_formula")
    String chemicalFormula;

    public ChemicalIngridient() {
    }

    public ChemicalIngridient(String name, BigDecimal price, String chemicalFormula) {
        super(name, price);
        this.chemicalFormula = chemicalFormula;
    }

    public String getChemicalFormula() {
        return chemicalFormula;
    }

    public void setChemicalFormula(String chemicalFormula) {
        this.chemicalFormula = chemicalFormula;
    }

}
