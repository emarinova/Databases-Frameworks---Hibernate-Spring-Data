package org.softuni.mostwanted.domain.dto;

import com.google.gson.annotations.Expose;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TownJSONImportDto implements Serializable {
    @NotNull
    @Expose
    private String name;

    public TownJSONImportDto() {
    }

    public TownJSONImportDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
