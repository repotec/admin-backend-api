package com.ntg.adm.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApplicationTlDTO {
	private long applicartionsTlId;
	private String applicationDisplayName;
	private BigDecimal applicationId;
	private String languageCode;
}
