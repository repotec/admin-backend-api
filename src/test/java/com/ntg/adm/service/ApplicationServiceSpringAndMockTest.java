package com.ntg.adm.service;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithUserDetails;

import com.ntg.adm.dao.ApplicationRepository;
import com.ntg.adm.dto.ApplicationDTO;
import com.ntg.adm.dto.mapper.ApplicationMapper;
import com.ntg.adm.model.AdmApplication;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
public class ApplicationServiceSpringAndMockTest {
	
	@MockBean
    private ApplicationRepository repository;
	
	@Autowired
    private ApplicationService service;

	@Autowired
    private ApplicationMapper appMapper;

    @Test 
    @WithUserDetails(userDetailsServiceBeanName = "userDetailsServiceImpl", value = "admin")
    void saveWhenNewApplication() {
    	//when
        final var expected = AdmApplication.builder().applicationName("Admino")
        												  .applicationUrl("http://admin.com")
        												  .image("test.png")
        												  .isActive("Y")
        												  .build();
        
        Mockito.when(repository.save(Mockito.any(AdmApplication.class))).thenReturn(expected);
        
        //then
        final ApplicationDTO actual = service.saveApplication(appMapper.entityToDto(expected));
        
        //assert
        MatcherAssert.assertThat(actual, Matchers.hasProperty("applicationName", Matchers.equalTo(expected.getApplicationName())));
        MatcherAssert.assertThat(actual, Matchers.hasProperty("applicationUrl", Matchers.equalTo(expected.getApplicationUrl())));
        MatcherAssert.assertThat(actual, Matchers.hasProperty("image", Matchers.equalTo(expected.getImage())));
        MatcherAssert.assertThat(actual, Matchers.hasProperty("isActive", Matchers.equalTo(expected.getIsActive())));
       
        //verify
        Mockito.verify(repository, Mockito.times(1)).save(ArgumentMatchers.any(AdmApplication.class));
        Mockito.verifyNoMoreInteractions(repository);
    }
    
    
    @Test 
    @WithUserDetails(userDetailsServiceBeanName = "userDetailsServiceImpl", value = "admin")
    void BDD_saveNewApplication() {
    	//given
        final var expectedApplication = AdmApplication.builder().applicationName("Admino")
		        												  .applicationUrl("http://admin.com")
		        												  .image("test.png")
		        												  .isActive("Y")
		        												  .build();
        
        BDDMockito.given(repository.save(Mockito.any(AdmApplication.class))).willReturn(expectedApplication);
        
        //when
        ApplicationDTO applDTO = appMapper.entityToDto(expectedApplication);
        final ApplicationDTO actual = service.saveApplication(applDTO);
        
        //assert
        MatcherAssert.assertThat(actual, Matchers.hasProperty("applicationName", Matchers.equalTo(expectedApplication.getApplicationName())));
        MatcherAssert.assertThat(actual, Matchers.hasProperty("applicationUrl", Matchers.equalTo(expectedApplication.getApplicationUrl())));
        MatcherAssert.assertThat(actual, Matchers.hasProperty("image", Matchers.equalTo(expectedApplication.getImage())));
        MatcherAssert.assertThat(actual, Matchers.hasProperty("isActive", Matchers.equalTo(expectedApplication.getIsActive())));
        
        //then
        BDDMockito.then(repository).should().save(ArgumentMatchers.any(AdmApplication.class));
    }
    
}




