package com.corporation.departmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corporation.departmentservice.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	Department findByDepartmentCode(String departmentCode);
}
