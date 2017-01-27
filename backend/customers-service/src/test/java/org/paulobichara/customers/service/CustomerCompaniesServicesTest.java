package org.paulobichara.customers.service;

import java.io.File;
import java.util.List;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.paulobichara.customers.domain.entity.CustomerCompany;
import org.paulobichara.customers.service.exceptions.CustomerCompaniesServicesException;

/**
 * Class implemented to test the CustomerCompaniesServices end point
 * @author Paulo Augusto Dacach Bichara
 *
 */
@RunWith(Arquillian.class)
public class CustomerCompaniesServicesTest 
{

    @Deployment
    public static WebArchive createDeployment() 
    {
    	File[] files = Maven.resolver().loadPomFromFile("pom.xml")
                .importRuntimeDependencies().resolve().withTransitivity().asFile();
    	
        WebArchive war = ShrinkWrap.create(WebArchive.class)
        		.addClass(CustomerCompaniesServices.class)
        		.addClass(CustomerCompaniesServicesException.class)        		
        		.addAsLibraries(files);
        
        return war;
    }
    
	@Test
    @SuppressWarnings("unchecked")	
    public void testAddCustomerCompany(@ArquillianResteasyResource CustomerCompaniesServices service)
	{
    	int sizeBefore = ((List<CustomerCompany>)service.getAllCustomerCompanies().getEntity()).size();	  
    	service.addCustomerCompany("Add Test Company", "Add Test Company Description");
    	int sizeAfter = ((List<CustomerCompany>)service.getAllCustomerCompanies().getEntity()).size();
    	Assert.assertEquals(sizeBefore + 1, sizeAfter);
    }
	
	@Test
    public void testUpdateCustomerCompany(@ArquillianResteasyResource CustomerCompaniesServices service) 
	{
    	CustomerCompany company = (CustomerCompany) service.addCustomerCompany(
    															"Update Test Company", 
    															"Update Test Company Description")
    														.getEntity();
    	
    	service.updateCustomerCompany(company.getId(), "Modified Update Test Company", company.getDescription());
    	CustomerCompany modifiedCompany = (CustomerCompany) service.getCustomerCompany(company.getId()).getEntity();
    	Assert.assertEquals("Modified Update Test Company", modifiedCompany.getName());
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testRemoveCustomerCompany(@ArquillianResteasyResource CustomerCompaniesServices service)
	{
    	CustomerCompany company = (CustomerCompany) service.addCustomerCompany(
    															"Remove Test Company", 
    															"Remove Test Company Description")
    														.getEntity();
    	
    	int sizeBefore = ((List<CustomerCompany>)service.getAllCustomerCompanies().getEntity()).size();	    	
    	service.removeCustomerCompany(company.getId());
    	int sizeAfter = ((List<CustomerCompany>)service.getAllCustomerCompanies().getEntity()).size();
    	Assert.assertEquals(sizeBefore - 1, sizeAfter);
	}

}
