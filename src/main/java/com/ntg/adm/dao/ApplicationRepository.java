package com.ntg.adm.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ntg.adm.model.AdmApplication;

/**
 * 1- Supported keywords inside method names (Query creation from method names)
 * 2- annotate your query method with @Query
 * 3- NamedQuery and NamedNativeQuery
 * 4- Specifications
 */
public interface ApplicationRepository extends CrudRepository<AdmApplication, Long>, JpaSpecificationExecutor<AdmApplication> {
	Page<AdmApplication> findAll(Pageable pageable);
	List<AdmApplication> findByApplicationNameIgnoreCaseContaining(String applicationName);
	List<AdmApplication> findAll();

	@EntityGraph(type = EntityGraphType.FETCH, attributePaths = {"admApplicationText"})
	List<AdmApplication> findAll(Sort sort);
	
	@Query("FROM AdmApplication a where lower(a.applicationName) like concat('%', lower(:name) ,'%') and lower(a.image) like concat('%', lower(:img) ,'%')")
	List<AdmApplication> findByNameByJPQLQuery(@Param("name") String applicationName, @Param("img") String image);

	List<AdmApplication> findAllByNameByNamedQuery(String applicationName);

	@Query("FROM AdmApplication a where lower(a.applicationName) like concat('%', lower(:name) ,'%') and lower(a.image) like concat('%', lower(:img) ,'%')")
	Page<AdmApplication> findByNameByJPQLQueryPageable(@Param("name") String applicationName, @Param("img") String image, Pageable pageable);

	Page<AdmApplication> findAll(Specification<AdmApplication> applicationCriteria, Pageable pageable);
}
