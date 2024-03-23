package com.corporation.employeeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
		description = "OrganizationDto Model Information"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
	private Long id;
	@Schema(description = "Employee first name")
	private String firstName;
	@Schema(description = "Employee last name")
	private String lastName;
	@Schema(description = "Employee email")
	private String email;
	@Schema(description = "Employee department code")
	private String departmentCode;
	@Schema(description = "Employee organiztion code")
	private String organizationCode;
}
