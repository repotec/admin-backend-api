package com.ntg.adm.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ntg.adm.dao.ApplicationRepository;
import com.ntg.adm.dto.ApplicationDTO;
import com.ntg.adm.dto.mapper.ApplicationMapper;
import com.ntg.adm.model.AdmApplication;
import com.ntg.adm.util.bundle.ResourceBundleUtil;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
//@Import(value = { MockitoConfig.class })
public class ApplicationServiceMockitoTest {

	@InjectMocks
    private ApplicationService service;

	@Mock
    private ApplicationRepository repository;
    
	@Mock
    private ResourceBundleUtil resourceBundleUtil;
	
	@Spy
	private ApplicationMapper appMapper = Mappers.getMapper(ApplicationMapper.class);
	
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
    void BDDfindApplicationById_test() {
    	//given
    	BDDMockito.<Optional<AdmApplication>>given(repository.findByApplicationId(1L)).willReturn(Optional.of(AdmApplication.builder().applicationId(1L).build()));
    	
    	//when
    	final ApplicationDTO actual = service.findApplicationById(1L);
    	
    	//assert
    	Assertions.assertThat(actual).isNotNull();
    	
    	//then
    	BDDMockito.then(repository).should(BDDMockito.times(1)).findByApplicationId(ArgumentMatchers.any(Long.class));
    }
    
    @Test
    void findApplicationById_test() {
    	//given
    	Mockito.<Optional<AdmApplication>>when(repository.findByApplicationId(1L)).thenReturn(Optional.of(AdmApplication.builder().applicationId(1L).build()));
    	
    	//when
    	final ApplicationDTO actual = service.findApplicationById(1L);
    	
    	//assert
    	Assertions.assertThat(actual).isNotNull();
    	
    	//verify
    	Mockito.verify(repository, Mockito.times(1)).findByApplicationId(ArgumentMatchers.any(Long.class));
    }
    
    @Test
    void testNotFoundApplicationById() {
    	Assertions.assertThatThrownBy(() ->  service.findApplicationById(22222221L))
    	.isInstanceOf(com.ntg.adm.exception.RecordNotFoundException.class);
    }
    
    @Test
    void testNullApplicationById() {
    	Assertions.assertThatThrownBy(() ->  service.findApplicationById(3L))
    	.isInstanceOf(com.ntg.adm.exception.RecordNotFoundException.class);
    }
}
