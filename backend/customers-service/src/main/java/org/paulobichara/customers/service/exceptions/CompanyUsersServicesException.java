package org.paulobichara.customers.service.exceptions;

/**
 * Class responsible for encapsulate exceptions thrown inside CompanyUsersServices methods
 * @author Paulo Augusto Dacach Bichara
 *
 */
public class CompanyUsersServicesException extends Exception {

	private static final long serialVersionUID = 5608389380392088404L;
	
	public CompanyUsersServicesException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getMessage() {
		return new StringBuilder().append("Some error occurred in CompanyUsersServices - ").append(this.getCause().getMessage()).toString();
	}
	
}
