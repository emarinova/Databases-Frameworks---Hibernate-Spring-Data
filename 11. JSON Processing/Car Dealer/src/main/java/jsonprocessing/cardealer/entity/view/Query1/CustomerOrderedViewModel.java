package jsonprocessing.cardealer.entity.view.Query1;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CustomerOrderedViewModel implements Serializable{
    private Long id;
    @Expose
    private String name;
    @Expose
    private Date birthDate;
    @Expose
    private boolean isYoungDriver;
    @Expose
    private Set<SaleViewModel> sales;

    public CustomerOrderedViewModel() {
        this.sales = new HashSet<>();
    }

    public CustomerOrderedViewModel(long id, String name, Date birthDate, boolean isYoungDriver) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
        this.sales = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<SaleViewModel> getSales() {
        return sales;
    }

    public void setSales(Set<SaleViewModel> sales) {
        this.sales = sales;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
