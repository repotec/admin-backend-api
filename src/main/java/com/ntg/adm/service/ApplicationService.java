package com.ntg.adm.service;

import static com.ntg.adm.dao.specification.ApplicationSpecification.applicationFullCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ntg.adm.base.BaseService;
import com.ntg.adm.base.FieldValueExists;
import com.ntg.adm.dao.ApplicationRepository;
import com.ntg.adm.dto.ApplicationDTO;
import com.ntg.adm.dto.mapper.ApplicationMapper;
import com.ntg.adm.exception.RecordNotFoundException;
import com.ntg.adm.model.AdmApplication;
import com.ntg.adm.util.bundle.ResourceBundleUtil;
import com.ntg.adm.util.query.SearchQuery;
import com.ntg.adm.util.query.SpecificationUtil;

@Service	
@Transactional
@Slf4j
public class ApplicationService extends BaseService<AdmApplication, Long> implements FieldValueExists {
	@Autowired
	ApplicationRepository repository;

	@Autowired
	ApplicationMapper mapper;
	
	@Autowired
	ResourceBundleUtil resourceBundleUtil;

	/**
	 *
	 * @param pageable
	 * @return
	 */
	public Page<AdmApplication> findAll(Pageable pageable){
		return repository.findAll(pageable);
	}

	/**
	 *
	 * @param sort
	 * @return
	 */
	public List<ApplicationDTO> findAll(String[] sort){
		
		List<Order> orders = new ArrayList<Order>();
		
		if(sort[0].contains(",")) {
			for (String sortOrder : sort) {
				String[] _sort = sortOrder.split(",");
		        orders.add(new Order(Sort.Direction.valueOf(_sort[1].toUpperCase()), _sort[0]));
		    }
		}
		
		List<ApplicationDTO> applicationDtoList = repository.findAll(Sort.by(orders)).stream().map(mapper::entityToDto).collect(Collectors.toList());
		
		return applicationDtoList;
	}

	/**
	 *
	 * @return
	 */
	public List<ApplicationDTO> findAll(){
		 List<ApplicationDTO> applicationDTO = repository.findAll().stream().map(mapper::entityToDto).collect(Collectors.toList());
		 
		 return applicationDTO;
	}

	/**
	 * 
	 * @param application
	 * @param applicationId
	 * @return
	 */
	public ApplicationDTO saveApplication(ApplicationDTO application){
		AdmApplication admApplication = repository.save(mapper.dtoToEntity(application));
		application.setApplicationId(admApplication.getApplicationId());
		return application;
	}

	/**
	 * 
	 * @param application
	 * @param applicationId
	 * @return
	 */
	public AdmApplication saveApplication(AdmApplication application){
		AdmApplication admApplication = repository.save(application);
		application.setApplicationId(admApplication.getApplicationId());
		return admApplication;
	}

	
	/**
	 *
	 * @param applicationId
	 * @param applicationName
	 * @param applicationUrl
	 * @param pageable
	 * @return
	 */
	@Cacheable(cacheNames = {"applications"}, key = "{#applicationId, #applicationName, #applicationUrl}")
	public Page<ApplicationDTO> findByApplicationsByRegularCriteria(long applicationId, String applicationName, String applicationUrl ,Pageable pageable){
		Specification<AdmApplication> specification = Specification.where(applicationFullCriteria(applicationId, applicationName, applicationUrl));

		return repository.findAll(specification, pageable).map(mapper::entityToDto);
	}
	
	/**
	 * 
	 * @param searchQuery
	 * @return
	 */
	@Cacheable(cacheNames = {"applications2"}, key = "{#root.methodName}")
	public Page<ApplicationDTO> findApplicationsByCriteria(SearchQuery searchQuery){
		Specification<AdmApplication> specification = SpecificationUtil.bySearchQuery(searchQuery, AdmApplication.class);
		PageRequest pageRequest = getPageRequest(searchQuery);
		
		return repository.findAll(specification, pageRequest).map(mapper::entityToDto);
	}

	/**
	 * Desc: Method created to find the application by applicationId
	 * @param applicationId
	 * @return
	 */
	@Cacheable(cacheNames = {"applications"}, key = "{#applicationId}")
	public ApplicationDTO findApplicationById(Long applicationId){
		return mapper.entityToDto(repository.findByApplicationId(applicationId).orElseThrow( ()-> new RecordNotFoundException(resourceBundleUtil.getMessage("resource.notFound.error"))));
	}

	/**
	 * Desc: Method created to delete the application by applicationId
	 * @param applicationId
	 */
	@Override
	@CacheEvict(cacheNames =  {"applications", "findApplicationsByCriteria"}, key = "{#applicationId}", allEntries = true)
	public void deleteEntityById(Long applicationId) {
		super.deleteEntityById(applicationId);
	}

	/**
	 *
	 * @param application
	 * @return
	 */
	@CachePut(cacheNames =  {"applications", "findApplicationsByCriteria"}, key = "{#applicationId}")
	public ApplicationDTO updateApplication(ApplicationDTO application) {
		if (!repository.findById(application.getApplicationId()).isPresent()) {
			throw new RecordNotFoundException(resourceBundleUtil.getMessage("resource.notFound.error"));
		}

		application.setApplicationId(application.getApplicationId());
		AdmApplication admApplication = repository.save(mapper.dtoToEntity(application));
		application.setApplicationId(admApplication.getApplicationId());
		return application;
	}

	/**
	 * 
	 */
	@Override
    public boolean fieldValueExists(Object value) throws UnsupportedOperationException {

        if (value == null) 
            return false;

        return this.repository.existsByApplicationName(value.toString());
    }
}
	