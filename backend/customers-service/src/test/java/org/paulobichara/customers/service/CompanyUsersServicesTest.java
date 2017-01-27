package org.paulobichara.customers.service;

import java.io.File;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.paulobichara.customers.domain.entity.CompanyUser;
import org.paulobichara.customers.domain.entity.CustomerCompany;
import org.paulobichara.customers.service.exceptions.CompanyUsersServicesException;
import org.paulobichara.customers.service.exceptions.CustomerCompaniesServicesException;
import org.paulobichara.customers.service.response.UserSearchResponse;

/**
 * Class implemented to test the CompanyUsersServices end point
 * @author Paulo Augusto Dacach Bichara
 *
 */
@RunWith(Arquillian.class)
public class CompanyUsersServicesTest 
{
	
    @Deployment
    public static WebArchive createDeployment() 
    {
    	File[] files = Maven.resolver().loadPomFromFile("pom.xml")
                .importRuntimeDependencies().resolve().withTransitivity().asFile();
    	
        WebArchive war = ShrinkWrap.create(WebArchive.class)
        		.addClass(CompanyUsersServices.class)
        		.addClass(CompanyUsersServicesException.class)       
        		.addClass(CustomerCompaniesServices.class)
        		.addClass(CustomerCompaniesServicesException.class)
        		.addClass(UserSearchResponse.class)        		
        		.addAsLibraries(files);
        
        return war;
    }
    
	@Test
    public void testAddCompanyUser(
    				@ArquillianResteasyResource CompanyUsersServices usersService, 
    				@ArquillianResteasyResource CustomerCompaniesServices companiesService)
	{
		CustomerCompany company = (CustomerCompany) companiesService.addCustomerCompany(
    																	"Add User Test Company", 
    																	"Add User Test Company Description")
    																.getEntity();
    	
    	int sizeBefore = ((UserSearchResponse)usersService.getCompanyUsers(null, 0, 1000, "id", true)
    						.getEntity()).getResults().size();
    	
    	usersService.addCompanyUser("First", "Last", "Software Engineer", "adduser@somedomain.com", company.getId());
    	
    	int sizeAfter = ((UserSearchResponse)usersService.getCompanyUsers(null, 0, 1000, "id", true)
    						.getEntity()).getResults().size();
    	
    	Assert.assertEquals(sizeBefore + 1, sizeAfter);
    }
	
	@Test
    public void testUpdateCompanyUser(
    				@ArquillianResteasyResource CompanyUsersServices usersService, 
    				@ArquillianResteasyResource CustomerCompaniesServices companiesService) 
	{
    	CustomerCompany company = (CustomerCompany) companiesService.addCustomerCompany(
    																		"Update User Test Company", 
    																		"Update User Test Company Description")
    																.getEntity();
    	
    	CompanyUser user = (CompanyUser) usersService.addCompanyUser(
    														"First", 
    														"Last", 
    														"Software Engineer", 
    														"updateuser@somedomain.com", 
    														company.getId())
    												.getEntity();
    	
    	usersService.updateCompanyUser(
    						user.getId(), 
    						"Changed", 
    						user.getLastName(), 
    						user.getRole(), 
    						user.getEmail(), 
    						user.getCompany().getId());
    	
    	CompanyUser modifiedUser = (CompanyUser) usersService.getCompanyUser(user.getId()).getEntity();
    	Assert.assertEquals("Changed", modifiedUser.getFirstName());
	}

	@Test
	public void testRemoveCompanyUser(
					@ArquillianResteasyResource CompanyUsersServices usersService, 
					@ArquillianResteasyResource CustomerCompaniesServices companiesService) 
	{
    	CustomerCompany company = (CustomerCompany) companiesService.addCustomerCompany(
    																	"Remove User Test Company", 
    																	"Remove User Test Company Description")
    																.getEntity();
    	
    	CompanyUser user = (CompanyUser) usersService.addCompanyUser(
    														"First", 
    														"Last", 
    														"Software Engineer", 
    														"removeuser@somedomain.com", 
    														company.getId()).getEntity();

    	int sizeBefore = ((UserSearchResponse)usersService.getCompanyUsers(null, 0, 1000, "id", true).getEntity())
    						.getResults().size();
    	
    	usersService.removeCompanyUser(user.getId());
    	
    	int sizeAfter = ((UserSearchResponse)usersService.getCompanyUsers(null, 0, 1000, "id", true).getEntity())
    						.getResults().size();
    	
    	Assert.assertEquals(sizeBefore - 1, sizeAfter);
	}
}
