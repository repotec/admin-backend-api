package com.ntg.adm.service;


import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;

import com.ntg.adm.dao.ApplicationRepository;
import com.ntg.adm.dto.ApplicationDTO;
import com.ntg.adm.dto.mapper.ApplicationMapper;
import com.ntg.adm.model.AdmApplication;

@SpringBootTest
public class ApplicationServiceSpringTest {

	@Autowired
    private ApplicationService service;

	@Autowired
    private ApplicationRepository repository;

	@Autowired
    private ApplicationMapper appMapper;

    @Test 
    @WithUserDetails(userDetailsServiceBeanName = "userDetailsServiceImpl", value = "admin")
    void saveNewApplication() {
        final var expected = AdmApplication.builder().applicationName("Admino")
        												  .applicationUrl("http://admin.com")
        												  .image("test.png")
        												  .isActive("Y")
        												  .build();
        
        final ApplicationDTO actual = service.saveApplication(appMapper.entityToDto(expected));

        MatcherAssert.assertThat(actual, Matchers.hasProperty("applicationName", Matchers.equalTo(expected.getApplicationName())));
        MatcherAssert.assertThat(actual, Matchers.hasProperty("applicationUrl", Matchers.equalTo(expected.getApplicationUrl())));
        MatcherAssert.assertThat(actual, Matchers.hasProperty("image", Matchers.equalTo(expected.getImage())));
        MatcherAssert.assertThat(actual, Matchers.hasProperty("isActive", Matchers.equalTo(expected.getIsActive())));
       
        Mockito.verify(repository, Mockito.times(1)).save(ArgumentMatchers.any(AdmApplication.class));
        Mockito.verifyNoMoreInteractions(repository);
    }
    
   
    
    @Test
    void test() {
//    	when(repository.findByApplicationId(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
//    	
//    	ApplicationDTO actual = service.findApplicationById(null);
//    	
//    	assertThat(actual, Matchers.not(equalTo(null)));
//    	
//    	assertThatThrownBy(() -> studentService.registerStudent(registrationRequest))
//        .isInstanceOf(DuplicateIdException.class)
//        .hasMessage("student record with matric number already exists");
    }
}
