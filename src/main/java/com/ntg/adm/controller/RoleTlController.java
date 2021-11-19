package com.ntg.adm.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ntg.adm.model.AdmRolesTl;
import com.ntg.adm.service.RolesTlService;

@RestController
@RequestMapping("/role/translations")
public class RoleTlController {

	@Autowired
	RolesTlService rolesTlService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<AdmRolesTl> getAllRoles(){
		return rolesTlService.findAll();
	}
	
	@RequestMapping(value = "/id", method = RequestMethod.GET)
	public Optional<AdmRolesTl> findRoleById(@RequestParam("roleId") long roleId){
		return rolesTlService.findById(roleId);
	}
	
	@RequestMapping(value = "/create" , method = RequestMethod.POST)
	public AdmRolesTl createNewRole(AdmRolesTl role){
		return rolesTlService.create(role);
	}
	
	@RequestMapping(value = "/update" , method = RequestMethod.PUT)
	public AdmRolesTl updateRole(AdmRolesTl role){
		return rolesTlService.update(role);
	}
	
	@RequestMapping(value = "/delete" , method = RequestMethod.DELETE)
	public AdmRolesTl deleteRole(AdmRolesTl role){
		return rolesTlService.delete(role);
	}
}
