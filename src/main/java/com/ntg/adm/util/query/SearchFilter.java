package com.ntg.adm.util.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchFilter {
	private String property;
	private String operator;
	private Object value;
}
