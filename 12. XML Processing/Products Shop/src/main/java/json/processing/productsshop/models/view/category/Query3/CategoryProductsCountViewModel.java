package json.processing.productsshop.models.view.category.Query3;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryProductsCountViewModel implements Serializable{
    @XmlAttribute(name = "name")
    private String name;
    @XmlElement(name = "products-count")
    private long productsCount;
    @XmlElement(name = "average-price")
    private Double averagePrice;
    @XmlElement(name = "total-revenue")
    private BigDecimal totalRevenue;

    public CategoryProductsCountViewModel() {
    }

    public CategoryProductsCountViewModel(String name, long productsCount, Double averagePrice, BigDecimal totalRevenue) {
        this.name = name;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(long productsCount) {
        this.productsCount = productsCount;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
