package com.ntg.adm.service;

import static com.ntg.adm.dao.specification.ApplicationSpecification.idCrtr;
import static com.ntg.adm.dao.specification.ApplicationSpecification.nameLikeCrtr;
import static com.ntg.adm.dao.specification.ApplicationSpecification.applicationFullCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ntg.adm.annotation.Audit;
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

public class ApplicationService extends BaseService<AdmApplication, Long> implements FieldValueExists {
	
	@Autowired
	ApplicationRepository admApplicationRepository;

	@Autowired
	ApplicationMapper applicationMapper;
	
	public Page<AdmApplication> findAll(Pageable pageable){
		return admApplicationRepository.findAll(pageable);
	}
	
	public List<ApplicationDTO> findAll(String[] sort){
		
		List<Order> orders = new ArrayList<Order>();
		
		if(sort[0].contains(",")) {
			for (String sortOrder : sort) {
				String[] _sort = sortOrder.split(",");
		        orders.add(new Order(Sort.Direction.valueOf(_sort[1].toUpperCase()), _sort[0]));
		    }
		}
		
		List<ApplicationDTO> applicationDtoList = admApplicationRepository.findAll(Sort.by(orders)).stream().map(applicationMapper::admApplicationToApplicationDto).collect(Collectors.toList());
		
		return applicationDtoList;
	}
	
	public List<ApplicationDTO> findAll(){
		 List<ApplicationDTO> applicationDTO = admApplicationRepository.findAll().stream().map(applicationMapper::admApplicationToApplicationDto).collect(Collectors.toList());
		 
		 return applicationDTO;
	}

	public Optional<AdmApplication> findById(long applicationId){
		return admApplicationRepository.findById(applicationId);
	}
	
	/**
	 * 
	 * @param application
	 * @param applicationId
	 * @return
	 */
	public ApplicationDTO createApplication(ApplicationDTO application, long applicationId){
		AdmApplication admApplication = admApplicationRepository.save(applicationMapper.ApplicationDtoToAdmApplication(application));
		application.setApplicationId(admApplication.getApplicationId());
		return application;
	}
	
	/**
	 * 
	 * @param application
	 * @param applicationId
	 * @return
	 */
	public ApplicationDTO updateApplication(ApplicationDTO application, long applicationId) {
		if (!admApplicationRepository.findById(applicationId).isPresent()) {
			throw new RecordNotFoundException(ResourceBundleUtil.getMessage("email.validation.error"));
		}
		
		application.setApplicationId(applicationId);
		AdmApplication admApplication = admApplicationRepository.save(applicationMapper.ApplicationDtoToAdmApplication(application));
		application.setApplicationId(admApplication.getApplicationId());
		return application;
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

		return admApplicationRepository.findAll(specification, pageable).map(applicationMapper::admApplicationToApplicationDto);
	}
	
	/**
	 * 
	 * @param searchQuery
	 * @return
	 */
	public Page<ApplicationDTO> findApplicationsByCriteria(SearchQuery searchQuery){
		Specification<AdmApplication> specification = SpecificationUtil.bySearchQuery(searchQuery, AdmApplication.class);
		PageRequest pageRequest = getPageRequest(searchQuery);
		
		return admApplicationRepository.findAll(specification, pageRequest).map(applicationMapper::admApplicationToApplicationDto);
	}
	
	
	@Cacheable(cacheNames = {"application"}, key = "{#applicationId}")
	public ApplicationDTO findApplicationById(Long applicationId){
		return applicationMapper.admApplicationToApplicationDto(admApplicationRepository.findByApplicationId(applicationId));
	}
	
	/**
	 * 
	 */
	@Override
    public boolean fieldValueExists(Object value) throws UnsupportedOperationException {

        if (value == null) 
            return false;

        return this.admApplicationRepository.existsByApplicationName(value.toString());
    }
}
	