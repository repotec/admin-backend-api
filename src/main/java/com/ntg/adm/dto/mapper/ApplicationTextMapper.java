package com.ntg.adm.dto.mapper;

import com.ntg.adm.dto.ApplicationTextDTO;
import com.ntg.adm.model.AdmApplicationText;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationTextMapper {
    ApplicationTextDTO appTextToAppTextDto(AdmApplicationText admApplicationsText);
    AdmApplicationText AppTextDtoToAppText(ApplicationTextDTO applicationTextDTO);
}
