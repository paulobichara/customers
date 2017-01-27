package org.paulobichara.customers.business.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.paulobichara.customers.domain.entity.CustomerCompany;

/**
 * Class that represents a CustomerCompany repository
 * @author Paulo Augusto Dacach Bichara
 *
 */
@Stateless
public class CustomerCompanyRepository 
{

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Method that finds a specific customer company by its name
	 * @param name customer company name to be searched
	 * @return fetched company
	 */
	public CustomerCompany findCustomerCompany(String name) 
	{
		TypedQuery<CustomerCompany> query = 
				this.entityManager.createNamedQuery("CustomerCompany.findByName", CustomerCompany.class);
		
		query.setParameter("prName", name);
		return query.getSingleResult();
	}
	
	/**
	 * Method that finds a specific customer company by its unique id
	 * @param id customer company unique id to be searched
	 * @return fetched company
	 */
	public CustomerCompany findCustomerCompany(Long id) 
	{
		return this.entityManager.find(CustomerCompany.class, id);
	}
	
	/**
	 * Method that adds a new customer company or updates an existing one
	 * @param company company to be added or updated
	 * @return persisted company
	 */
	public CustomerCompany addOrUpdateCustomerCompany(CustomerCompany company) 
	{
		if (company.getId() == null) 
		{
			this.entityManager.persist(company);			
		} 
		else 
		{
			company = this.entityManager.merge(company);
		}
		return company;
	}
	
	/**
	 * Method that removes a specific customer company by its unique id
	 * @param id customer company unique id
	 */
	public void removeCustomerCompany(Long id) 
	{
		Query queryUsers = this.entityManager.createNamedQuery("CompanyUser.removeByCompanyId");
		queryUsers.setParameter("prCompanyId", id);
		queryUsers.executeUpdate();
		Query queryCompany = this.entityManager.createNamedQuery("CustomerCompany.removeById");
		queryCompany.setParameter("prId", id);
		queryCompany.executeUpdate();
	}
	
	/**
	 * Method that returns all the customer companies in the database
	 * @return all the customer companies in the database
	 */
	public List<CustomerCompany> getAllCustomerCompanies() 
	{
		return this.entityManager.createNamedQuery("CustomerCompany.listAll", CustomerCompany.class).getResultList();
	}	
	
}
