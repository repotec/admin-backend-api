package com.ntg.adm.dto.mapper;

import com.ntg.adm.dto.ApplicationDTO;
import com.ntg.adm.dto.ApplicationTlDTO;
import com.ntg.adm.model.AdmApplication;
import com.ntg.adm.model.AdmApplicationsTl;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationTranslationMapper {
    ApplicationTlDTO appTransToAppTransDto(AdmApplicationsTl admApplicationsTl);
    AdmApplication AppTransDtoToAppTrans(ApplicationTlDTO applicationTlDTO);
}
