package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.DeptWithRegionName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentServiceExtended {
    Page<DeptWithRegionName> getDepartmentsWithRegionName(String region, Integer threshold, Pageable pageable);
}
