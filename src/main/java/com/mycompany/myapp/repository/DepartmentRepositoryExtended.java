package com.mycompany.myapp.repository;

import com.mycompany.myapp.service.dto.DeptWithRegionName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepositoryExtended extends DepartmentRepository{
    @Query(value = "SELECT " +
        "new com.mycompany.myapp.service.dto.DeptWithRegionName(" +
        "department.id," +
        "department.name," +
        "department.population," +
        "department.departmentNumber," +
        "region.name AS regionName," +
        "region.id AS regionId" +
        ") " +
        "FROM Department department " +
        "JOIN department.region region " +
        "WHERE (region.name LIKE CONCAT('%',:region,'%')) " +
        "AND (:populationMin IS NULL OR department.population > :populationMin)")
    Page<DeptWithRegionName> getDepartmentsWithPagination(@Param("region") String regionName, @Param("populationMin") Integer populationMin, Pageable pageable);
}
