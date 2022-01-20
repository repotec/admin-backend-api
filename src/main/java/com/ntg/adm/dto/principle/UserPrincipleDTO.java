package com.ntg.adm.dto.principle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserPrincipleDTO {
	private Long userId;
    private String userName;
    private String password;
    private List<UserRolePrincipleDTO> userRolesPrinciple;
}
