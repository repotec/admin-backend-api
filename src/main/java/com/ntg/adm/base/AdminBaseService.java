package com.ntg.adm.base;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ntg.adm.exception.RecordNotFoundException;

public abstract class AdminBaseService<T extends AdminBaseEntity, ID extends Number> {

	@Autowired
	AdminBaseRepository<T, ID> baseRepository;
	
	
	public void deleteEntityById(ID id) {
		Optional<T> entity = baseRepository.findById(id);
		if (!entity.isPresent())
			throw new RecordNotFoundException("resource is not found");
		
		baseRepository.sofDeleteById(id);
	}
	
}
