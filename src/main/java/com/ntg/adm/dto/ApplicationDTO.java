package com.ntg.adm.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import com.ntg.adm.model.AdmApplication;

public class ApplicationDTO implements Serializable {
	private static final long serialVersionUID = -333085798418750181L;
	
	private long applicationId;
	
	private String applicationName;
	
	private String applicationUrl;
	
	@Pattern(regexp = "(.*/)*.+\\.(png|jpg|gif|bmp|jpeg|PNG|JPG|GIF|BMP|JPEG)$")
	private String image;
	
	private String isActive;
	
	/**
	 *  convert JPA entity to dto
	 * @param application
	 */
	public ApplicationDTO(AdmApplication application) {
		this(application.getApplicationId(), 
			 application.getApplicationName(),
			 application.getApplicationUrl(), 
			 application.getImage(), 
			 application.getIsActive());
	}
	
	public ApplicationDTO() {
	}
	
	public ApplicationDTO(long applicationId, String applicationName, String applicationUrl, String image, String isActive) {
		this.applicationId = applicationId;
		this.applicationName = applicationName;
		this.applicationUrl = applicationUrl;
		this.image = image;
		this.isActive = isActive;
	}

	public long getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationName() {
		return this.applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getApplicationUrl() {
		return this.applicationUrl;
	}

	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getIsActive() {
		return this.isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
}
