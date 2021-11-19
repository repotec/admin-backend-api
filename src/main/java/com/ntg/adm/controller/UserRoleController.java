package com.ntg.adm.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ntg.adm.model.AdmRole;
import com.ntg.adm.service.RoleService;

@RestController
@RequestMapping("/roles")
public class UserRoleController {

	@Autowired
	RoleService roleService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<AdmRole> getAllRoles(){
		return roleService.findAll();
	}
	
	@RequestMapping(value = "/id", method = RequestMethod.GET)
	public Optional<AdmRole> findRoleById(@RequestParam("roleId") long roleId){
		return roleService.findById(roleId);
	}
	
	@RequestMapping(value = "/create" , method = RequestMethod.POST)
	public AdmRole createNewRole(AdmRole role){
		return roleService.create(role);
	}
	
	@RequestMapping(value = "/update" , method = RequestMethod.PUT)
	public AdmRole updateRole(AdmRole role){
		return roleService.update(role);
	}
	
	@RequestMapping(value = "/delete" , method = RequestMethod.DELETE)
	public AdmRole deleteRole(AdmRole role){
		return roleService.delete(role);
	}
}
