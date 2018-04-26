package jsonprocessing.cardealer.entity.binding;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class SupplierCreateBindingModel implements Serializable{
    @Expose
    private String name;
    @Expose
    private boolean isImporter;

    public SupplierCreateBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
