package com.ntg.adm.util.query;

import java.util.List;

public class SearchQuery {
	private List<SearchFilter> searchFilter;
	private SortOrder sortOrder;
	private Integer pageNumber;
	private Integer pageSize;
	
	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<SearchFilter> getSearchFilter() {
		return searchFilter;
	}

	public void setSearchFilter(List<SearchFilter> searchFitler) {
		this.searchFilter = searchFitler;
	}
	
	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
}
