import React, { Component } from 'react';
import Griddle from 'griddle-react';

export default class RecordsTable extends Component {

	constructor(props) {
		super(props);

		this.state = { 
			"results": [],
			"filter" : "",
          	"currentPage": 0,
          	"maxPages": 0,
          	"externalResultsPerPage": 5,
          	"externalSortColumn": "id",
          	"externalSortAscending": true
      	};

      	this.setPage = this.setPage.bind(this);
		this.changeSort = this.changeSort.bind(this);
		this.setFilter = this.setFilter.bind(this);
		this.setPageSize = this.setPageSize.bind(this);
	}

    fetchExternalData(filter, currentPage, resultsPerPage, sortColumn, sortAsc) {
    	const baseUrl = "https://localhost/customers";
    	const url = baseUrl + "/api/rest/users"
    				+ "?filter=" + encodeURIComponent(filter ? filter : "")
    				+ "&resultsPerPage=" + resultsPerPage
    				+ "&currentPage=" + currentPage
    				+ "&sortField=" + sortColumn
    				+ "&sortAscending=" + sortAsc;
    	const casUrl = baseUrl + "/cas/login?service=";

	    fetch(url, {
	      credentials: 'include'
	    })
	    .then(result=> {
	      if (result.url && result.url.indexOf(casUrl) === 0) {
	        window.location.href = casUrl + encodeURIComponent(window.location.href);
	        return false;
	      } else {
	        return result.json();
	      }
	    })
	    .then(response=>this.setState({ 
	    					"filter" : response.filter, 
	    					"results" : response.results, 
	    					"maxPages" : response.numberOfPages,
	    					"externalResultsPerPage" : response.resultsPerPage,
	    					"currentPage" : response.currentPage,
	    					"externalSortColumn" : response.sortField,
	    					"externalSortAscending" : response.sortAscending }));
    }

	componentDidMount() {
		this.fetchExternalData(
				this.state.filter, 
				this.state.currentPage, 
				this.state.externalResultsPerPage,
				this.state.externalSortColumn,
				this.state.externalSortAscending);
	}

    //what page is currently viewed
    setPage(index) {
		//This should interact with the data source to get the page at the given index
	    index = index > this.state.maxPages ? this.state.maxPages : index < 0 ? 0 : index;
	    this.fetchExternalData(
	      		this.state.filter,
	      		index,
	      		this.state.externalResultsPerPage,
				this.state.externalSortColumn,
				this.state.externalSortAscending);
    }

    //this changes whether data is sorted in ascending or descending order
    changeSort(sort, sortAscending) {
		this.fetchExternalData(
				this.state.filter, 
				this.state.currentPage, 
				this.state.externalResultsPerPage,
				sort,
				sortAscending);
    }

    //this method handles the filtering of the data
    setFilter(filter) {
		this.fetchExternalData(
				filter, 
				this.state.currentPage, 
				this.state.externalResultsPerPage,
				this.state.externalSortColumn,
				this.state.externalSortAscending);
    }

    //this method handles determining the page size
    setPageSize(size) {
		this.fetchExternalData(
				this.state.filter, 
				this.state.currentPage, 
				size,
				this.state.externalSortColumn,
				this.state.externalSortAscending);
    }

	render() {
		return (
			<Griddle     
				tableClassName={'table table-bordered table-striped table-hover'}
			    useGriddleStyles={true}
			    showFilter={true}
			    showSettings={true}
			    settingsToggleClassName='btn btn-default'
			    useExternal={true} externalSetPage={this.setPage} 
		        externalChangeSort={this.changeSort} externalSetFilter={this.setFilter}
		        externalSetPageSize={this.setPageSize} externalMaxPage={this.state.maxPages}
		        externalCurrentPage={this.state.currentPage} results={this.state.results} 
		        resultsPerPage={this.state.externalResultsPerPage} 
		        externalSortColumn={this.state.externalSortColumn} 
		        externalSortAscending={this.state.externalSortAscending}/>
		);
	}

}