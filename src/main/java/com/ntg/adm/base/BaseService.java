package com.ntg.adm.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ntg.adm.model.AdmApplication;
import com.ntg.adm.util.bundle.ResourceBundleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import com.ntg.adm.exception.RecordNotFoundException;
import com.ntg.adm.util.query.SearchQuery;
import com.ntg.adm.util.query.SortOrder;

public abstract class BaseService<T extends BaseEntity, ID extends Number> {

	@Autowired
	BaseRepository<T, ID> baseRepository;
	
	/**
	 * 
	 * @param id
	 */
	public void deleteEntityById(ID id) {
		Optional<T> entity = baseRepository.findById(id);
		if (!entity.isPresent())
			throw new RecordNotFoundException(ResourceBundleUtil.getMessage("resource.notFound.error"));
		
		baseRepository.sofDeleteById(id);
	}
	
	/**
	 * 
	 * @param searchQuery
	 * @return
	 */
	public PageRequest getPageRequest(SearchQuery searchQuery) {

		Integer pageNumber = searchQuery.getPageNumber() == null ? 0 : searchQuery.getPageNumber();
		Integer pageSize = searchQuery.getPageSize() == null ? 10 : searchQuery.getPageSize();

		List<Sort.Order> orders;
		Sort sort = null;
		
		if(searchQuery != null) {
			SortOrder sortOrder = searchQuery.getSortOrder();
			
			if(sortOrder != null) {
				List<String> ascProps = sortOrder.getAscendingOrder();
				orders = new ArrayList<>();
				
				if(ascProps != null || !ascProps.isEmpty()) {
					for (String prop : ascProps) {
						orders.add(Sort.Order.asc(prop));
					}
				}
		
				List<String> descProps = sortOrder.getDescendingOrder();
		
				if (descProps != null && !descProps.isEmpty()) {
					for (String prop : descProps) {
						orders.add(Sort.Order.desc(prop));
					}
		
				}
				
				sort = Sort.by(orders);
				return PageRequest.of(pageNumber, pageSize, sort);
			}else {
				return PageRequest.of(0, 10);
			}
			
		}
		
		return null;
	}
}
