package json.processing.productsshop.models.binding.category;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesCreate {
    @XmlElement(name = "category")
    private List<CategoryCreateBindingModel> categories;

    public CategoriesCreate() {
        this.categories = new ArrayList<>();
    }

    public CategoriesCreate(List<CategoryCreateBindingModel> categories) {
        this.categories = categories;
    }

    public List<CategoryCreateBindingModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryCreateBindingModel> categories) {
        this.categories = categories;
    }
}
