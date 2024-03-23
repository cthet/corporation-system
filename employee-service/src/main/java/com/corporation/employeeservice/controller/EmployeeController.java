package com.corporation.employeeservice.controller;

import com.corporation.employeeservice.dto.APIResponseDto;
import com.corporation.employeeservice.dto.EmployeeDto;
import com.corporation.employeeservice.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
		name = "Employee Service - EmployeeController",
		description = "Employee Controller Exposes REST APIs for Employee-Service"
		
)
@RestController
@RequestMapping("api/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@Operation(
			summary = "Save Employee REST API",
			description = "Save Employee REST API is used to save employee object in a database"
	)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP Status 201 CREATED"
	)
	@PostMapping
	public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
		EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);
		return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
	}
	
	@Operation(
			summary = "Get Employee REST API",
			description = "Get Employee REST API is used to get employee object from a database"
	)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS"
	)
	@GetMapping("{employeeId}")
	public ResponseEntity<APIResponseDto> getEmployee(@PathVariable Long employeeId){
		APIResponseDto apiResponseDto = employeeService.getEmployeeById(employeeId);
		return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
	}
	

}
