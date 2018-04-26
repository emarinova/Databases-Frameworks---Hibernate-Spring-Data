package jsonprocessing.cardealer.entity.view.Query3;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class SupplierLocalViewModel implements Serializable {
    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private Long partsCount;

    public SupplierLocalViewModel(Long id, String name, Long partsCount) {
        this.id = id;
        this.name = name;
        this.partsCount = partsCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(Long partsCount) {
        this.partsCount = partsCount;
    }
}
