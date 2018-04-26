package json.processing.productsshop.models.binding.category;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class CategoryCreateBindingModel implements Serializable{
    @Expose
    private String name;

    public CategoryCreateBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
