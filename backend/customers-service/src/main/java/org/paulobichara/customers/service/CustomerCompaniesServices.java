package org.paulobichara.customers.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.paulobichara.customers.business.repository.CustomerCompanyRepository;
import org.paulobichara.customers.domain.entity.CustomerCompany;
import org.paulobichara.customers.service.exceptions.CustomerCompaniesServicesException;

/**
 * 
 * Class representing the REST customer companies services end point
 * 
 * @author Paulo Augusto Dacach Bichara
 *
 */
@Path("/companies")
public class CustomerCompaniesServices {

	@EJB
	private CustomerCompanyRepository customerCompaniesRepo;

	/**
	 * @api {get} /companies/ Get all customer companies
	 * @apiName GetAllCustomerCompanies
	 * @apiGroup CustomerCompany
	 *
	 * @apiSuccess {Object[]} data List of customer companies objects
	 * 
	 * @apiError CustomerCompaniesServicesException Some error occurred in CustomerCompaniesServices - <code>[error-message]</code>
	 * 
	 */	
	/**
	 * Method implementing a REST service responsible for listing all customer companies
	 *  
	 * @return REST Response object containing fetched data
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)	
	public Response getAllCustomerCompanies() {
		try {
			List<CustomerCompany> companies = this.customerCompaniesRepo.getAllCustomerCompanies();
			return Response.status(Status.OK).entity(companies).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new CustomerCompaniesServicesException(e).getMessage()).build();
		}
	}	

	/**
	 * @api {get} /companies/:companyId Get a specific customer company
	 * @apiName GetCustomerCompany
	 * @apiGroup CustomerCompany
	 *
	 * @apiParam {Number} companyId Customer company unique identifier
	 *
	 * @apiSuccess {Number} id customer company unique identifier
	 * @apiSuccess {String} name customer company name
	 * @apiSuccess {String} description customer company description 
	 * 
	 * @apiError CustomerCompaniesServicesException Some error occurred in CustomerCompaniesServices - <code>[error-message]</code>
	 * 	
	 */		
	/**
	 * Method implementing a REST service responsible for find a specific customer company by its id.
	 *  
	 * @param  companyId  customer company unique identifier
	 * @return REST Response object containing requested customer company
	 */
	@GET
	@Path("{companyId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomerCompany(@PathParam("companyId") Long companyId) {
		try {
			CustomerCompany company = this.customerCompaniesRepo.findCustomerCompany(companyId);
			return Response.status(200).entity(company).build();
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new CustomerCompaniesServicesException(e).getMessage()).build();
		}
	}

	/**
	 * @api {delete} /companies/:companyId Remove a specific customer company
	 * @apiName RemoveCustomerCompany
	 * @apiGroup CustomerCompany
	 *
	 * @apiParam {Number} companyId Customer company unique identifier
	 *
	 * @apiSuccess {String} message <code>Company removed successfully</code>
	 * 
	 * @apiError CustomerCompaniesServicesException Some error occurred in CustomerCompaniesServices - <code>[error-message]</code>
	 * 	
	 */		
	/**
	 * Method implementing a REST service responsible for remove a specific customer company by its id.
	 *  
	 * @param  companyId  customer company unique identifier
	 * @return REST Response object containing a TEXT_PLAIN success message
	 */
	@DELETE
	@Path("{companyId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response removeCustomerCompany(@PathParam("companyId") Long companyId) {
		try {
			this.customerCompaniesRepo.removeCustomerCompany(companyId);
			return Response.status(200).entity("Company removed successfully").build();
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new CustomerCompaniesServicesException(e).getMessage()).build();
		}
	}

	/**
	 * @api {put} /companies/:companyId Update a specific customer company
	 * @apiName UpdateCustomerCompany
	 * @apiGroup CustomerCompany
	 *
	 * @apiParam {Number} companyId Customer company unique identifier
	 * @apiParam {String} name [QUERY PARAMETER] Customer company name
	 * @apiParam {String} description [QUERY PARAMETER] Customer company description
	 *
	 * @apiSuccess {Number} id customer company unique identifier
	 * @apiSuccess {String} name customer company name
	 * @apiSuccess {String} description customer company description 
	 * 
	 * @apiError CustomerCompaniesServicesException Some error occurred in CustomerCompaniesServices - <code>[error-message]</code>
	 * 	
	 */		
	/**
	 * Method implementing a REST service responsible for update a specific customer company by its id.
	 *  
	 * @param companyId Customer company unique identifier
	 * @param name Customer company name
	 * @param description Customer company description
	 * @return REST Response object containing updated customer company
	 */
	@PUT
	@Path("{companyId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCustomerCompany(@PathParam("companyId") Long companyId, 
			@QueryParam("name") String name, @QueryParam("description") String description) {
		try {
			CustomerCompany company = new CustomerCompany();
			company.setName(name);
			company.setDescription(description);
			company.setId(companyId);
			company = this.customerCompaniesRepo.addOrUpdateCustomerCompany(company);
			return Response.status(200).entity(company).build();
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new CustomerCompaniesServicesException(e).getMessage()).build();
		}
	}
	
	/**
	 * @api {put} /companies/ Add a new customer company
	 * @apiName AddCustomerCompany
	 * @apiGroup CustomerCompany
	 *
	 * @apiParam {String} name [QUERY PARAMETER] Customer company name
	 * @apiParam {String} description [QUERY PARAMETER] Customer company description
	 *
	 * @apiSuccess {Number} id customer company unique identifier
	 * @apiSuccess {String} name customer company name
	 * @apiSuccess {String} description customer company description 
	 * 
	 * @apiError CustomerCompaniesServicesException Some error occurred in CustomerCompaniesServices - <code>[error-message]</code>
	 * 	
	 */		
	/**
	 * Method implementing a REST service responsible for add a new customer company.
	 *  
	 * @param name Customer company name
	 * @param description Customer company description
	 * @return REST Response object containing added customer company
	 */	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCustomerCompany(@QueryParam("name") String name, @QueryParam("description") String description) {
		try {
			CustomerCompany company = new CustomerCompany();
			company.setName(name);
			company.setDescription(description);
			company = this.customerCompaniesRepo.addOrUpdateCustomerCompany(company);
			return Response.status(200).entity(company).build();
		} catch(Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new CustomerCompaniesServicesException(e).getMessage()).build();
		}
	}	
}
