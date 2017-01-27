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

import org.paulobichara.customers.business.repository.CompanyUserRepository;
import org.paulobichara.customers.domain.entity.CompanyUser;
import org.paulobichara.customers.domain.entity.CustomerCompany;
import org.paulobichara.customers.service.exceptions.CompanyUsersServicesException;
import org.paulobichara.customers.service.response.UserSearchResponse;

/**
 * 
 * Class representing the REST company users services end point
 * 
 * @author Paulo Augusto Dacach Bichara
 *
 */
@Path("/users")
public class CompanyUsersServices 
{

	@EJB
	private CompanyUserRepository companyUsersRepo;

	/**
	 * @api {get} /users/ Get all company users
	 * @apiName GetCompanyUsers
	 * @apiGroup CompanyUser
	 *
	 * @apiParam {String} filter Filter to be applied to the users database
	 * @apiParam {Number} currentPage Search's current page (pagination parameter)
	 * @apiParam {Number} resultsPerPage Number of records to be returned
	 * @apiParam {String} sortField Field used as sorting column
	 * @apiParam {Boolean} sortAscending Parameter that indicates the sort direction
	 * 
	 * @apiSuccess {CompanyUser[]} data List of company user objects
	 * 
	 * @apiError CompanyUsersServicesException Some error occurred in CompanyUsersServices - <code>[error-message]</code>
	 * 
	 */
	/**
	 * Method implementing a REST service responsible for filtering all company users with pagination and sorting
	 * 
	 * @param filter Filter to be applied to the users database
	 * @param currentPage Search's current page (pagination parameter)
	 * @param resultsPerPage Number of records to be returned
	 * @param sortField Field used as sorting column
	 * @param sortAscending Parameter that indicates the sort direction
	 * @return REST Response object containing fetched data
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompanyUsers(
			@QueryParam("filter") String filter,
			@QueryParam("currentPage") int currentPage,
			@QueryParam("resultsPerPage") int resultsPerPage,
			@QueryParam("sortField") String sortField,
			@QueryParam("sortAscending") boolean sortAscending)
	{
		try 
		{
			List<CompanyUser> users = this.companyUsersRepo.getCompanyUsers(
																	filter, 
																	currentPage, 
																	resultsPerPage, 
																	sortField, 
																	sortAscending);
			
			UserSearchResponse searchResult = new UserSearchResponse();
			searchResult.setCurrentPage(currentPage);
			searchResult.setResultsPerPage(resultsPerPage);
			searchResult.setResults(users);
			searchResult.setSortField(sortField);
			searchResult.setSortAscending(sortAscending);
			searchResult.setFilter(filter);
			
			long numberOfRecords = this.companyUsersRepo.countCompanyUsers(filter);
			
			if (resultsPerPage > 0)
			{
				long numberOfPages =  numberOfRecords / resultsPerPage;
				if (numberOfRecords % resultsPerPage > 0)
				{
					numberOfPages += 1;
				}				
				
				searchResult.setNumberOfPages(numberOfPages);				
			}
			
			return Response.status(Status.OK).entity(searchResult).build();
		} 
		catch (Exception e) 
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new CompanyUsersServicesException(e).getMessage()).build();
		}
	}	

	/**
	 * @api {get} /users/:companyUserId Get a specific company user
	 * @apiName GetCompanyUser
	 * @apiGroup CompanyUser
	 *
	 * @apiParam {Number} companyUserId Company user unique identifier
	 *
	 * @apiSuccess {Number} id company user unique identifier
	 * @apiSuccess {String} firstName company user first name
	 * @apiSuccess {String} lastName company user last name
	 * @apiSuccess {String} role company user role in company 
	 * @apiSuccess {String} email company user email
	 * @apiSuccess {Object} company company that the user works for  
	 * 
	 * @apiError CompanyUsersServicesException Some error occurred in CompanyUsersServices - <code>[error-message]</code>
	 * 	
	 */		
	/**
	 * Method implementing a REST service responsible for find a specific company user by its id.
	 *  
	 * @param  companyUserId  customer company unique identifier
	 * @return REST Response object containing requested company user
	 */
	@GET
	@Path("{companyUserId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompanyUser(@PathParam("companyUserId") Long companyUserId) 
	{
		try 
		{
			CompanyUser user = this.companyUsersRepo.findCompanyUser(companyUserId);
			return Response.status(200).entity(user).build();
		} 
		catch(Exception e) 
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new CompanyUsersServicesException(e).getMessage()).build();
		}
	}

	/**
	 * @api {delete} /users/:companyUserId Remove a specific company user
	 * @apiName RemoveCompanyUser
	 * @apiGroup CompanyUser
	 *
	 * @apiParam {Number} companyUserId Company user unique identifier
	 *
	 * @apiSuccess {String} message <code>User removed successfully</code>
	 * 
	 * @apiError CompanyUsersServicesException Some error occurred in CompanyUsersServices - <code>[error-message]</code>
	 * 	
	 */		
	/**
	 * Method implementing a REST service responsible for remove a specific company user by its id.
	 *  
	 * @param  companyUserId  company user unique identifier
	 * @return REST Response object containing a TEXT_PLAIN success message
	 */
	@DELETE
	@Path("{companyUserId}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response removeCompanyUser(@PathParam("companyUserId") Long companyUserId) 
	{
		try 
		{
			this.companyUsersRepo.removeCompanyUser(companyUserId);
			return Response.status(200).entity("User removed successfully").build();
		} 
		catch(Exception e) 
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new CompanyUsersServicesException(e).getMessage()).build();
		}
	}

	/**
	 * @api {put} /users/:companyUserId Update a specific company user
	 * @apiName UpdateCompanyUser
	 * @apiGroup CompanyUser
	 *
	 * @apiParam {Number} companyUserId Company user unique identifier
	 * @apiParam {String} firstName [QUERY PARAMETER] company user first name
	 * @apiParam {String} lastName [QUERY PARAMETER] company user last name
	 * @apiParam {String} role [QUERY PARAMETER] company user role in company
	 * @apiParam {String} email [QUERY PARAMETER] company user email
	 * @apiParam {Number} companyId [QUERY PARAMETER] Customer company id that the user works for
	 *
	 * @apiSuccess {Number} id company user unique identifier
	 * @apiSuccess {String} firstName company user first name
	 * @apiSuccess {String} lastName company user last name
	 * @apiSuccess {String} role company user role in company 
	 * @apiSuccess {String} email company user email
	 * @apiSuccess {Object} company company that the user works for  
	 * 
	 * @apiError CompanyUsersServicesException Some error occurred in CompanyUsersServices - <code>[error-message]</code>
	 * 	
	 */
	/**
	 * Method implementing a REST service responsible for update a specific company user by its id.
	 * 
	 * @param companyUserId company user unique identifier
	 * @param firstName company user first name
	 * @param lastName company user last name
	 * @param role company user role in company
	 * @param email company user email
	 * @param companyId company id that the user works for
	 * @return REST Response object containing updated company user
	 */
	@PUT
	@Path("{companyUserId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCompanyUser(
			@PathParam("companyUserId") Long companyUserId, 
			@QueryParam("firstName") String firstName, 
			@QueryParam("lastName") String lastName,
			@QueryParam("role") String role, 
			@QueryParam("email") String email,
			@QueryParam("companyId") Long companyId) 
	{
		try
		{
			CustomerCompany company = new CustomerCompany();
			company.setId(companyId);
			CompanyUser user = new CompanyUser();
			user.setId(companyUserId);
			user.setCompany(company);
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setRole(role);
			user = this.companyUsersRepo.addOrUpdateCompanyUser(user);
			return Response.status(200).entity(user).build();
		}
		catch(Exception e)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new CompanyUsersServicesException(e).getMessage()).build();
		}
	}

	/**
	 * @api {put} /users/ Add a new company user
	 * @apiName AddCompanyUser
	 * @apiGroup CompanyUser
	 *
	 * @apiParam {String} firstName [QUERY PARAMETER] company user first name
	 * @apiParam {String} lastName [QUERY PARAMETER] company user last name
	 * @apiParam {String} role [QUERY PARAMETER] company user role in company
	 * @apiParam {String} email [QUERY PARAMETER] company user email
	 * @apiParam {Number} companyId [QUERY PARAMETER] Customer company id that the user works for
	 *
	 * @apiSuccess {Number} id company user unique identifier
	 * @apiSuccess {String} firstName company user first name
	 * @apiSuccess {String} lastName company user last name
	 * @apiSuccess {String} role company user role in company 
	 * @apiSuccess {String} email company user email
	 * @apiSuccess {Object} company company that the user works for  
	 * 
	 * @apiError CompanyUsersServicesException Some error occurred in CompanyUsersServices - <code>[error-message]</code>
	 * 	
	 */		
	/**
	 * Method implementing a REST service responsible for add a new company user.
	 * 
	 * @param firstName company user first name
	 * @param lastName company user last name
	 * @param role company user role in company
	 * @param email company user email
	 * @param companyId company id that the user works for
	 * @return REST Response object containing updated company user
	 */	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCompanyUser(
			@QueryParam("firstName") String firstName, 
			@QueryParam("lastName") String lastName, 
			@QueryParam("role") String role, 
			@QueryParam("email") String email, 
			@QueryParam("companyId") Long companyId) 
	{
		try 
		{
			CustomerCompany company = new CustomerCompany();
			company.setId(companyId);
			CompanyUser user = new CompanyUser();
			user.setCompany(company);
			user.setEmail(email);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setRole(role);
			user = this.companyUsersRepo.addOrUpdateCompanyUser(user);
			return Response.status(200).entity(user).build();
		}
		catch(Exception e) 
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(new CompanyUsersServicesException(e).getMessage()).build();
		}
	}
}
