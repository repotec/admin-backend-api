package com.ntg.adm.base;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID> {
	default void sofDeleteById(ID id) {
		this.softDelete(findById(id).orElseThrow(
				() -> new EmptyResultDataAccessException(String.format("No %s entity with id %s exists!", "", id), 1)));
	}

	default void softDelete(T entity) {
		((BaseEntity) entity).setIsDeleted('1');
		save(entity);
	}
	
	@Override
    default void deleteById(ID id) {
        throw new UnsupportedOperationException();
    }

    @Override
    default void delete(T entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    default void deleteAll(Iterable<? extends T> entities) {
        throw new UnsupportedOperationException();
    }

    @Override
    default void deleteAll() {
        throw new UnsupportedOperationException();
    }
}
