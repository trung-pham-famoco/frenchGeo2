package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.CityService;
import com.mycompany.myapp.domain.City;
import com.mycompany.myapp.repository.CityRepository;
import com.mycompany.myapp.service.dto.CityDTO;
import com.mycompany.myapp.service.mapper.CityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link City}.
 */
@Service
@Transactional
public class CityServiceImpl implements CityService {

    private final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);

    private final CityRepository cityRepository;

    private final CityMapper cityMapper;

    public CityServiceImpl(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    /**
     * Save a city.
     *
     * @param cityDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CityDTO save(CityDTO cityDTO) {
        log.debug("Request to save City : {}", cityDTO);
        City city = cityMapper.toEntity(cityDTO);
        city = cityRepository.save(city);
        return cityMapper.toDto(city);
    }

    /**
     * Get all the cities.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {
        log.debug("Request to get all Cities");
        return cityRepository.findAll().stream()
            .map(cityMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one city by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CityDTO> findOne(Long id) {
        log.debug("Request to get City : {}", id);
        return cityRepository.findById(id)
            .map(cityMapper::toDto);
    }

    /**
     * Delete the city by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete City : {}", id);
        cityRepository.deleteById(id);
    }
}
