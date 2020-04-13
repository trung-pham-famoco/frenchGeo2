package com.mycompany.myapp.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

/**
 * A Region.
 */
@Entity
@Table(name = "region")
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(unique = true)
    private City prefecture;

    @OneToMany(mappedBy = "region")
    private Set<Department> departments = new HashSet<>();

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

    public Region name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getPrefecture() {
        return prefecture;
    }

    public Region prefecture(City city) {
        this.prefecture = city;
        return this;
    }

    public void setPrefecture(City city) {
        this.prefecture = city;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public Region departments(Set<Department> departments) {
        this.departments = departments;
        return this;
    }

    public Region addDepartments(Department department) {
        this.departments.add(department);
        department.setRegion(this);
        return this;
    }

    public Region removeDepartments(Department department) {
        this.departments.remove(department);
        department.setRegion(null);
        return this;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Region)) {
            return false;
        }
        return id != null && id.equals(((Region) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Region{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
