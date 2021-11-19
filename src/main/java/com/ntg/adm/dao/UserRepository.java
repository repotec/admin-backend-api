package com.ntg.adm.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import com.ntg.adm.model.AdmUser;

public interface UserRepository extends CrudRepository<AdmUser, Long> {
	@EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "userPrinciple-graph")
	AdmUser getByUserName(String userName);
}
