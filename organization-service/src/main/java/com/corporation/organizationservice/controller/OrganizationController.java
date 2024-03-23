package com.corporation.organizationservice.controller;

import com.corporation.organizationservice.dto.OrganizationDto;
import com.corporation.organizationservice.service.OrganizationService;
import com.corporation.organizationservice.service.impl.OrganizationServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
		name = "Organization Service - OrganizationController",
		description = "Organization Controller Exposes REST APIs for Organization-Service"
		
)
@RestController
@RequestMapping("api/organizations")
public class OrganizationController {

    private OrganizationService organizationService;


    public OrganizationController(OrganizationServiceImpl organizationService) {
        this.organizationService = organizationService;
    }

	@Operation(
			summary = "Save Organizaiton REST API",
			description = "Save Organizaiton REST API is used to save organization object in a database"
	)
	@ApiResponse(
			responseCode = "201",
			description = "HTTP Status 201 CREATED"
	)
    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody  OrganizationDto organizationDto){
        OrganizationDto saveOrganization = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(saveOrganization, HttpStatus.CREATED);
    }

	@Operation(
			summary = "Get Organizaiton REST API",
			description = "Get Organizaiton REST API is used to get organization object from a database"
	)
	@ApiResponse(
			responseCode = "200",
			description = "HTTP Status 200 SUCCESS"
	)
    @GetMapping("{code}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable("code")  String organizationCode){
        OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
        return ResponseEntity.ok(organizationDto);
    }
}
