package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Department.
 */
@Entity
@Table(name = "department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "population", nullable = false)
    private Integer population;

    @NotNull
    @Column(name = "department_number", nullable = false, unique = true)
    private String departmentNumber;

    @OneToMany(mappedBy = "department")
    private Set<City> cities = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("departments")
    private Region region;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Department name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public Department population(Integer population) {
        this.population = population;
        return this;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public Department departmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
        return this;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public Set<City> getCities() {
        return cities;
    }

    public Department cities(Set<City> cities) {
        this.cities = cities;
        return this;
    }

    public Department addCities(City city) {
        this.cities.add(city);
        city.setDepartment(this);
        return this;
    }

    public Department removeCities(City city) {
        this.cities.remove(city);
        city.setDepartment(null);
        return this;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public Region getRegion() {
        return region;
    }

    public Department region(Region region) {
        this.region = region;
        return this;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Department)) {
            return false;
        }
        return id != null && id.equals(((Department) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Department{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", population=" + getPopulation() +
            ", departmentNumber='" + getDepartmentNumber() + "'" +
            "}";
    }
}
