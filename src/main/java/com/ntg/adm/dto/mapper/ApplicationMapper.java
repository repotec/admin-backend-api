package com.ntg.adm.dto.mapper;

import com.ntg.adm.dto.ApplicationDTO;
import com.ntg.adm.model.AdmApplication;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    ApplicationDTO admApplicationToApplicationDto(AdmApplication admApplication);
    AdmApplication ApplicationDtoToAdmApplication(ApplicationDTO applicationDTO);
}
