package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.CityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link City} and its DTO {@link CityDTO}.
 */
@Mapper(componentModel = "spring", uses = {DepartmentMapper.class})
public interface CityMapper extends EntityMapper<CityDTO, City> {

    @Mapping(source = "department.id", target = "departmentId")
    CityDTO toDto(City city);

    @Mapping(source = "departmentId", target = "department")
    City toEntity(CityDTO cityDTO);

    default City fromId(Long id) {
        if (id == null) {
            return null;
        }
        City city = new City();
        city.setId(id);
        return city;
    }
}
