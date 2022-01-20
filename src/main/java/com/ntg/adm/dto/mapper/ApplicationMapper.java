package com.ntg.adm.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ntg.adm.dto.ApplicationDTO;
import com.ntg.adm.dto.ApplicationTextDTO;
import com.ntg.adm.model.AdmApplication;

@Mapper(componentModel = "spring", uses = ApplicationTextDTO.class)
public interface ApplicationMapper {
    @Mapping(target = "applicationId", source = "applicationId")
    @Mapping(target = "applicationName", source = "applicationName")
    @Mapping(target = "applicationUrl", source = "applicationUrl")
    @Mapping(target = "image", source = "image")
    @Mapping(target = "isActive", source = "isActive")
    @Mapping(target = "applicationText", source = "admApplication.admApplicationText")
	ApplicationDTO admApplicationToApplicationDto(AdmApplication admApplication);
    
    @Mapping(target = "applicationId", source = "applicationId")
    @Mapping(target = "applicationName", source = "applicationName")
    @Mapping(target = "applicationUrl", source = "applicationUrl")
    @Mapping(target = "image", source = "image")
    @Mapping(target = "isActive", source = "isActive")
    @Mapping(target = "admApplicationText", source = "applicationDTO.applicationText")
    AdmApplication ApplicationDtoToAdmApplication(ApplicationDTO applicationDTO);
}
