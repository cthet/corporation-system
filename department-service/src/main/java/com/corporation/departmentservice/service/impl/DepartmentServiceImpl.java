package com.corporation.departmentservice.service.impl;

import com.corporation.departmentservice.mapper.DepartmentMapper;
import com.corporation.departmentservice.repository.DepartmentRepository;
import com.corporation.departmentservice.service.DepartmentService;

import org.springframework.stereotype.Service;

import com.corporation.departmentservice.dto.DepartmentDto;
import com.corporation.departmentservice.entity.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	
	private DepartmentRepository departmentRepository;

	public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {

		Department department = DepartmentMapper.mapToDepartment(departmentDto);
		
		Department savedDepartment = departmentRepository.save(department);

		DepartmentDto savedDepartmentDto = DepartmentMapper.mapToDepartmentDto(savedDepartment);

		return savedDepartmentDto;

	}

	@Override
	public DepartmentDto getDepartmentDtoByCode(String code) {

		Department department = departmentRepository.findByDepartmentCode(code);

		DepartmentDto departmentDto = DepartmentMapper.mapToDepartmentDto(department);

		return departmentDto;

	}

}
