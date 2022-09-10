package com.ntg.adm.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.StaticMessageSource;

import com.ntg.adm.TestConfig;
import com.ntg.adm.dao.ApplicationRepository;
import com.ntg.adm.dto.ApplicationDTO;
import com.ntg.adm.dto.mapper.ApplicationMapper;
import com.ntg.adm.model.AdmApplication;
import com.ntg.adm.util.bundle.ResourceBundleUtil;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ApplicationServiceMockingTest {

	@InjectMocks
    private ApplicationService service;

	@Mock
    private ApplicationRepository repository;
    
	@Mock
    private ResourceBundleUtil resourceBundleUtil;
	
	@Spy
	private ApplicationMapper appMapper = Mappers.getMapper(ApplicationMapper.class);

	@Spy
	ReloadableResourceBundleMessageSource messages = messageSource();
	
	@Value("${bundle.baseName}")
	private String baseName;
	
	@Value("${bundle.defaultLocale}")
	private String defaultLocale;
	
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
		resource.setBasenames("classpath:" + baseName);
		resource.setDefaultEncoding("UTF-8");
		resource.setUseCodeAsDefaultMessage(true);
		resource.setCacheSeconds(-1); //default
		return resource;
	}
	
	
    @Test
    void shouldSaveOneApplication_test() {
        // When
        final var expected = AdmApplication.builder().applicationName("Admino")
        												  .applicationUrl("http://admin.com")
        												  .image("test.png")
        												  .isActive("Y")
        												  .build();

        when(repository.save(any(AdmApplication.class))).thenReturn(expected);
        
        final ApplicationDTO actual = service.saveApplication(appMapper.entityToDto(expected));

        //Assert
        assertThat(actual, Matchers.hasProperty("applicationName", Matchers.equalTo(expected.getApplicationName())));
        assertThat(actual, Matchers.hasProperty("applicationUrl", Matchers.equalTo(expected.getApplicationUrl())));
        assertThat(actual, Matchers.hasProperty("image", Matchers.equalTo(expected.getImage())));
        assertThat(actual, Matchers.hasProperty("isActive", Matchers.equalTo(expected.getIsActive())));
        
        Mockito.verify(repository, Mockito.times(1)).save(any(AdmApplication.class));
        Mockito.verifyNoMoreInteractions(repository);
    }
    
    
    @Test
    void testNotFoundApplicationById() {
    	Assertions.assertThatThrownBy(() ->  service.findApplicationById(22222221L))
    	.isInstanceOf(com.ntg.adm.exception.RecordNotFoundException.class);
    }
    
    @Test
    void testNullApplicationById() {
    	Assertions.assertThatThrownBy(() ->  service.findApplicationById(null))
    	.isInstanceOf(com.ntg.adm.exception.RecordNotFoundException.class);
    }
}
