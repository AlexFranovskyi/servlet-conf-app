package ua.conference.servletapp.support;

import java.util.List;

/**
 * Generic version of the Page class.
 * @param <T> the type of the value being boxed
 */

public class Page<T> {
	private int pageNumber;
	private int totalPages;
	private String sort = "";
	private List<T> list;
	
	public Page(List<T> list, String sort, int begin, int totalRows, int pageSize){
		this.list = list;
		this.sort = sort;
		
		this.pageNumber = begin / pageSize;
		this.totalPages = totalRows / pageSize + 1;
	}
	
	public Page() {	
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
