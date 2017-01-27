import React, { Component } from 'react';
import { Navbar, Nav, NavItem } from 'react-bootstrap';

export default class NavigationBar extends Component {

	constructor(props) {
		super(props);
		this.onLogout = this.onLogout.bind(this);
	}

	onLogout() {
		window.location.href = "https://localhost/customers/cas/logout";
	}

	render() {
		return (
		  <Navbar inverse collapseOnSelect>
		    <Navbar.Header>
		      <Navbar.Brand>
		        <a href="#">React-Bootstrap</a>
		      </Navbar.Brand>
		      <Navbar.Toggle />
		    </Navbar.Header>
		    <Navbar.Collapse>
		      <Nav pullRight>
		        <NavItem eventKey={2} href="#" onClick={this.onLogout}>Logout</NavItem>
		      </Nav>
		    </Navbar.Collapse>
		  </Navbar>
		);
	}

}