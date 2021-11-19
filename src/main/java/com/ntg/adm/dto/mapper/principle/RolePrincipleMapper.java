package com.ntg.adm.dto.mapper.principle;

import com.ntg.adm.dto.principle.RolePrincipleDTO;
import com.ntg.adm.model.AdmRole;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolePrincipleMapper {
    RolePrincipleDTO roleToRolePrinciple(AdmRole admRole);
}
