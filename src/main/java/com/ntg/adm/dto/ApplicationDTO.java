package com.ntg.adm.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO implements Serializable {
	private static final long serialVersionUID = -333085798418750181L;
	
	private long applicationId;
	
	@NotNull(message = "application name cannot be empty")
	@Size(max=50, min=5, message = "Application Name length must be great then {min} and less than {max} '${validatedValue}'")
	private String applicationName;
	
	private String applicationUrl;
	
	@Pattern(regexp = "(.*/)*.+\\.(png|jpg|gif|bmp|jpeg|PNG|JPG|GIF|BMP|JPEG)$")
	private String image;
	
	private String isActive;
	
	private List<ApplicationTextDTO> applicationText;
}
