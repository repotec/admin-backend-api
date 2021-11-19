package com.ntg.adm.dao;

import org.springframework.data.repository.CrudRepository;

import com.ntg.adm.model.AdmFunction;

public interface FunctionRepository extends CrudRepository<AdmFunction, Long> {

}
