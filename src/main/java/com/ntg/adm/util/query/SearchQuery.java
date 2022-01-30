package com.ntg.adm.util.query;

import java.util.List;

public class SearchQuery {
	private List<SearchFilter> searchFitler;
	private SortOrder sortOrder;
	Integer pageNumber;
	Integer pageSize;
	
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

	public List<SearchFilter> getSearchFitler() {
		return searchFitler;
	}

	public void setSearchFitler(List<SearchFilter> searchFitler) {
		this.searchFitler = searchFitler;
	}
	
	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
}
