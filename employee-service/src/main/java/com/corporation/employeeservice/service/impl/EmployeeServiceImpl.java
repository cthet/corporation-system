package com.corporation.employeeservice.service.impl;

import com.corporation.employeeservice.dto.APIResponseDto;
import com.corporation.employeeservice.dto.EmployeeDto;
import com.corporation.employeeservice.dto.OrganizationDto;
import com.corporation.employeeservice.entity.Employee;
import com.corporation.employeeservice.mapper.EmployeeMapper;
import com.corporation.employeeservice.repository.EmployeeRepository;
import com.corporation.employeeservice.service.APIClient;
import com.corporation.employeeservice.service.EmployeeService;
import com.corporation.employeeservice.dto.DepartmentDto;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	private EmployeeRepository employeeRepository;
	private WebClient webClient;
	private APIClient apiClient;

		

	public EmployeeServiceImpl(EmployeeRepository employeeRepository, WebClient webClient, APIClient apiClient) {
		this.employeeRepository = employeeRepository;
        this.webClient = webClient;
        this.apiClient = apiClient;
    }

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		
		Employee savedEmployee = employeeRepository.save(employee);

		return EmployeeMapper.mapToEmployeeDto(savedEmployee);

	}

	//@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	@Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	@Override
	public APIResponseDto getEmployeeById(Long employeeId) {

		LOGGER.info("inside getEmployeeById() method");
        Employee employee = employeeRepository.findById(employeeId).get();

		DepartmentDto departmentDto = webClient
				.get()
				.uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
				.retrieve()
				.bodyToMono(DepartmentDto.class)
				.block();

	//	DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
		OrganizationDto organizationDto = webClient.get()
				.uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode())
				.retrieve()
				.bodyToMono(OrganizationDto.class)
				.block();

		EmployeeDto employeeDto =  EmployeeMapper.mapToEmployeeDto(employee);

		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setEmployee(employeeDto);
		apiResponseDto.setDepartment(departmentDto);
		apiResponseDto.setOrganization(organizationDto);


		return apiResponseDto;
	}

	public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception){

		LOGGER.info("inside getDefaultDepartment() method");
		Employee employee = employeeRepository.findById(employeeId).get();

		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentName("R&D Department");
		departmentDto.setDepartmentCode("RD001");
		departmentDto.setDepartmentDescription("Research and Development Department");

		EmployeeDto employeeDto =  EmployeeMapper.mapToEmployeeDto(employee);

		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setEmployee(employeeDto);
		apiResponseDto.setDepartment(departmentDto);


		return apiResponseDto;
	}

}
