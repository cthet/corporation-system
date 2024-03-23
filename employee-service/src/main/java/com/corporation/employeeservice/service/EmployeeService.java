package com.corporation.employeeservice.service;

import com.corporation.employeeservice.dto.APIResponseDto;
import com.corporation.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
	EmployeeDto saveEmployee(EmployeeDto employeeDto);

	APIResponseDto getEmployeeById(Long employeeId);
}
