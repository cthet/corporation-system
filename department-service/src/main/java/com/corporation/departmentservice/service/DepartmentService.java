package com.corporation.departmentservice.service;

import com.corporation.departmentservice.dto.DepartmentDto;

public interface DepartmentService {
	DepartmentDto saveDepartment(DepartmentDto departmentDto);

	DepartmentDto getDepartmentDtoByCode(String code);
}
