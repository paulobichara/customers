package org.paulobichara.customers.service.response;

import java.io.Serializable;
import java.util.List;

import org.paulobichara.customers.domain.entity.CompanyUser;
import org.paulobichara.customers.service.CompanyUsersServices;

/**
 * Class representing a search response from the {@link CompanyUsersServices}
 * @author Paulo Augusto Dacach Bichara
 *
 */
public class UserSearchResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long numberOfPages;
	private int resultsPerPage;
	private int currentPage;
	private List<CompanyUser> results;
	private String sortField;
	private boolean sortAscending;
	private String filter;
	
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	
	public long getNumberOfPages() {
		return numberOfPages;
	}
	public void setNumberOfPages(long numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	
	public int getResultsPerPage() {
		return resultsPerPage;
	}
	public void setResultsPerPage(int resultsPerPage) {
		this.resultsPerPage = resultsPerPage;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public List<CompanyUser> getResults() {
		return results;
	}
	public void setResults(List<CompanyUser> results) {
		this.results = results;
	}
	
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	
	public boolean isSortAscending() {
		return sortAscending;
	}
	public void setSortAscending(boolean sortAscending) {
		this.sortAscending = sortAscending;
	}
}
