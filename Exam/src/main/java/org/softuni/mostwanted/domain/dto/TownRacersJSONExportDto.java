package org.softuni.mostwanted.domain.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class TownRacersJSONExportDto implements Serializable {
    @Expose
    private String name;
    @Expose
    private long racers;

    public TownRacersJSONExportDto() {
    }

    public TownRacersJSONExportDto(String name, long racers) {
        this.name = name;
        this.racers = racers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRacers() {
        return racers;
    }

    public void setRacers(long racers) {
        this.racers = racers;
    }
}
