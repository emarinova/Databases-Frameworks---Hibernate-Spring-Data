package json.processing.productsshop.models.view.category.Query3;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
public class CategoriesViewModel implements Serializable{
    @XmlElement(name = "category")
    private List<CategoryProductsCountViewModel> categories;

    public CategoriesViewModel() {
        this.categories = new ArrayList<>();
    }

    public CategoriesViewModel(List<CategoryProductsCountViewModel> categories) {
        this.categories = categories;
    }

    public List<CategoryProductsCountViewModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryProductsCountViewModel> categories) {
        this.categories = categories;
    }
}
