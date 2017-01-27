package org.paulobichara.customers.service.exceptions;

/**
 * Class responsible for encapsulate exceptions thrown inside CustomerCompaniesServices methods
 * @author Paulo Augusto Dacach Bichara
 *
 */
public class CustomerCompaniesServicesException extends Exception {

	private static final long serialVersionUID = 5608389380392088404L;
	
	public CustomerCompaniesServicesException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getMessage() {
		return new StringBuilder().append("Some error occurred in CustomerCompaniesServices - ").append(this.getCause().getMessage()).toString();
	}
	
}
