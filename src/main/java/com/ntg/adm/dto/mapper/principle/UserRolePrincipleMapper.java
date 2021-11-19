package com.ntg.adm.dto.mapper.principle;

import com.ntg.adm.dto.principle.UserRolePrincipleDTO;
import com.ntg.adm.model.AdmUserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",  uses = RolePrincipleMapper.class)
public interface UserRolePrincipleMapper {
    @Mapping(target = "rolePrinciple", source = "admUserRole.admRole")
    UserRolePrincipleDTO userRoleToUserRolePrinciple(AdmUserRole admUserRole);
}
