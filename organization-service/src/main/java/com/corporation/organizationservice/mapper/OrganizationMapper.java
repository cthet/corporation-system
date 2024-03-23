package com.corporation.organizationservice.mapper;

import com.corporation.organizationservice.dto.OrganizationDto;
import com.corporation.organizationservice.entity.Organization;

public class OrganizationMapper {

    public static OrganizationDto mapToOrganizationDto(Organization organization){
        return new OrganizationDto(
                organization.getId(),
                organization.getOrganizationDescription(),
                organization.getOrganizationCode(),
                organization.getOrganizationCode(),
                organization.getCreatedDate()
        );
    }

    public static Organization mapToOrganization(OrganizationDto organizationDto){
        return new Organization(
                organizationDto.getId(),
                organizationDto.getOrganizationDescription(),
                organizationDto.getOrganizationCode(),
                organizationDto.getOrganizationCode(),
                organizationDto.getCreatedDate()
        );
    }
}
