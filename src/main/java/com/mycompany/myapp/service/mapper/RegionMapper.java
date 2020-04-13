package com.mycompany.myapp.service.mapper;


import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.RegionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Region} and its DTO {@link RegionDTO}.
 */
@Mapper(componentModel = "spring", uses = {CityMapper.class})
public interface RegionMapper extends EntityMapper<RegionDTO, Region> {

    @Mapping(source = "prefecture.id", target = "prefectureId")
    RegionDTO toDto(Region region);

    @Mapping(source = "prefectureId", target = "prefecture")
    @Mapping(target = "departments", ignore = true)
    @Mapping(target = "removeDepartments", ignore = true)
    Region toEntity(RegionDTO regionDTO);

    default Region fromId(Long id) {
        if (id == null) {
            return null;
        }
        Region region = new Region();
        region.setId(id);
        return region;
    }
}
