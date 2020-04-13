package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.repository.DepartmentRepositoryExtended;
import com.mycompany.myapp.service.DepartmentServiceExtended;
import com.mycompany.myapp.service.dto.DeptWithRegionName;
import com.mycompany.myapp.service.mapper.DepartmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Primary
@Service
@Transactional
public class DepartmentServiceImplExtended extends DepartmentServiceImpl implements DepartmentServiceExtended {
    private final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepositoryExtended departmentRepositoryExtended;

    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImplExtended(DepartmentRepositoryExtended departmentRepositoryExtended, DepartmentMapper departmentMapper) {
        super(departmentRepositoryExtended, departmentMapper);
        this.departmentRepositoryExtended = departmentRepositoryExtended;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public Page<DeptWithRegionName> getDepartmentsWithRegionName(String region, Integer threshold, Pageable pageable) {
        log.debug("Request to get Departments with their region's name");
        return departmentRepositoryExtended.getDepartmentsWithPagination(region, threshold, pageable);
    }
}
