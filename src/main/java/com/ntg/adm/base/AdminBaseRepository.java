package com.ntg.adm.base;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AdminBaseRepository<T extends AdminBaseEntity, ID> extends JpaRepository<T, ID> {
	default void sofDeleteById(ID id) {
		this.softDelete(findById(id).orElseThrow(
				() -> new EmptyResultDataAccessException(String.format("No %s entity with id %s exists!", "", id), 1)));
	}

	default void softDelete(T entity) {
		((AdminBaseEntity) entity).setIsDeleted('1');
		save(entity);
	}
}
