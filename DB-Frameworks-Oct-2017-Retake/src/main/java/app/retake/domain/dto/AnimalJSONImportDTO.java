package app.retake.domain.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class AnimalJSONImportDTO implements Serializable {
    @Expose
    @Size(min = 3, max = 20)
    private String name;
    @Expose
    @Size(min = 3, max = 20)
    private String type;
    @Expose
    @DecimalMin("0.01")
    private Integer age;
    @Expose
    private PassportJSONImportDTO passport;

    public AnimalJSONImportDTO() {
    }

    public AnimalJSONImportDTO(String name, String type, Integer age, PassportJSONImportDTO passport) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public PassportJSONImportDTO getPassport() {
        return passport;
    }

    public void setPassport(PassportJSONImportDTO passport) {
        this.passport = passport;
    }
}
