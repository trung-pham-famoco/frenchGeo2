package com.mycompany.myapp.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

public class DeptWithRegionName implements Serializable {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer population;

    @NotNull
    private String departmentNumber;

    @NotNull
    private String regionName;

    @NotNull
    private Long regionId;

    public DeptWithRegionName(Long id, String name, Integer population, String departmentNumber, String regionName, Long regionId) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.departmentNumber = departmentNumber;
        this.regionName = regionName;
        this.regionId = regionId;
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

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeptWithRegionName deptWithRegionName = (DeptWithRegionName) o;
        if (deptWithRegionName.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deptWithRegionName.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeptWithRegionName{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", population=" + getPopulation() +
            ", departmentNumber='" + getDepartmentNumber() + "'" +
            ", regionName=" + getRegionName() +
            ", regionId=" + getRegionId() +
            "}";
    }
}
