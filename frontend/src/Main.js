import React, { Component } from 'react';
import NavigationBar from './NavigationBar';
import RecordsTable from './RecordsTable';

export default class Main extends Component {

	render() {
		  return (
		  	<div>
		  		<NavigationBar />
		  		<RecordsTable />
		  	</div>
		  );
	}

}