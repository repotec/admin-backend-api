package com.ntg.adm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.ntg.adm.dto.mapper.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ntg.adm.dto.ApplicationDTO;
import com.ntg.adm.exception.RecordNotFoundException;
import com.ntg.adm.response.SuccessResponse;
import com.ntg.adm.service.ApplicationService;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

	@Autowired
	ApplicationService applicationService;

	@Autowired
	ApplicationMapper applicationMapper;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<ApplicationDTO>> findAllApplications(@PageableDefault(page = 0, size = 2)
																	@SortDefault.SortDefaults({
																							@SortDefault(sort = "applicationName", direction = Sort.Direction.DESC),
																							@SortDefault(sort = "applicationId", direction = Sort.Direction.ASC)
																							}) Pageable pageable) {
		Page<ApplicationDTO> apps = applicationService.findAll(pageable).map(applicationMapper::admApplicationToApplicationDto);

		//return new ResponseEntity<>(new SuccessResponse<>(applicationService.findAll().stream().map(applicationMapper::admApplicationToApplicationDto).collect(Collectors.toList())), HttpStatus.OK);

		return new ResponseEntity<>(apps, HttpStatus.OK);
	}

	@RequestMapping(value = "/id", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse<ApplicationDTO>> getApplicationById(@RequestParam(name="applicationId") long applicationId) {
		return new ResponseEntity<>(new SuccessResponse<>(applicationMapper.admApplicationToApplicationDto(applicationService.findById(applicationId).orElseThrow(()-> new RecordNotFoundException("application is not found")))), HttpStatus.OK);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse<List<ApplicationDTO>>> findByApplicationName(@RequestParam(name="name") String applicationName,
																					   @RequestParam(name="image") String applicationImage) {
		return new ResponseEntity<>(new SuccessResponse<>(applicationService.findByApplicationName(applicationName, applicationImage).stream().map(applicationMapper::admApplicationToApplicationDto).collect(Collectors.toList())), HttpStatus.OK);
	}

	@RequestMapping(value = "/search/pageable", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse<Page<ApplicationDTO>>> findByApplicationNamePageable(@RequestParam(name="name") String applicationName,
																							   @RequestParam(name= "id") long applicationId,
																							   @PageableDefault(page = 0, size = 2)
																								   @SortDefault.SortDefaults({
																										   @SortDefault(sort = "applicationName", direction = Sort.Direction.DESC),
																										   @SortDefault(sort = "applicationId", direction = Sort.Direction.ASC)
																								   }) Pageable pageable) {

		return new ResponseEntity<>(new SuccessResponse<>(applicationService.findByApplicationCriteria(applicationName,
																											applicationId,
																											pageable).
				map(applicationMapper::admApplicationToApplicationDto)), HttpStatus.OK);
	}


	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse<ApplicationDTO>> createApplication(@Valid @RequestBody ApplicationDTO application) {
		return new ResponseEntity<>(new SuccessResponse<>(applicationService.createApplication(application, 0)), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse<ApplicationDTO>> updateApplication(@PathVariable(name = "id") long applicationId, @Valid @RequestBody ApplicationDTO application) {
		return new ResponseEntity<>(new SuccessResponse<>( applicationService.updateApplication(application, applicationId)), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SuccessResponse<String>> deleteApplication(@PathVariable(name = "id") long applicationId) {
		applicationService.deleteApplication(applicationId);
		return new ResponseEntity<>(new SuccessResponse<>("application has been deleted"), HttpStatus.OK);
	}
}
		