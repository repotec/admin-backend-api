package com.ntg.adm.dao;


import org.springframework.data.repository.CrudRepository;

import com.ntg.adm.model.AdmUserRole;

public interface UserRoleRepository extends CrudRepository<AdmUserRole, Long> {
	
}
