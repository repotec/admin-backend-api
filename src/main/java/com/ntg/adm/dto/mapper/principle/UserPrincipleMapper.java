package com.ntg.adm.dto.mapper.principle;

import com.ntg.adm.dto.principle.UserPrincipleDTO;
import com.ntg.adm.model.AdmUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserRolePrincipleMapper.class)
public interface UserPrincipleMapper {
    @Mapping(target = "userName", source = "userName")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "userRolesPrinciple", source = "admUser.admUserRoles")
    UserPrincipleDTO UserToUserPrinciple(AdmUser admUser);
}