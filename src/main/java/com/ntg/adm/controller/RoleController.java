package com.ntg.adm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ntg.adm.model.AdmUserRole;
import com.ntg.adm.service.UserRoleService;

@RestController
@RequestMapping("/user/roles")
public class RoleController {

	@Autowired
	UserRoleService userRoleService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<AdmUserRole> getAllUserRoles(){
		return userRoleService.findAll();
	}
}
