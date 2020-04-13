package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.DepartmentService;
import com.mycompany.myapp.service.DepartmentServiceExtended;
import com.mycompany.myapp.service.dto.DeptWithRegionName;
import io.github.jhipster.web.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class DepartmentResourceExtended {

    private final Logger log = LoggerFactory.getLogger(DepartmentResource.class);

    private static final String ENTITY_NAME = "department";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DepartmentServiceExtended departmentServiceExtended;

    public DepartmentResourceExtended(DepartmentServiceExtended departmentServiceExtended) {
        this.departmentServiceExtended = departmentServiceExtended;
    }

    @GetMapping("/departments/getDepartmentsWithFilter")
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public ResponseEntity<List<DeptWithRegionName>> getDepartmentsWithFilter(@RequestParam("threshold") Optional<Integer> pThreshold, @RequestParam("region") Optional<String> pRegion, Pageable pageable) {
        Integer threshold = null;
        if (pThreshold.isPresent()) {
            threshold = pThreshold.get();
        }
        String region = "";
        if (pRegion.isPresent()) {
            region = pRegion.get();
        }
        log.debug("REST request to get all departments filtered by region and a population threshold: {}, {}", threshold, region);
        final Page<DeptWithRegionName> page = departmentServiceExtended.getDepartmentsWithRegionName(region, threshold, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
