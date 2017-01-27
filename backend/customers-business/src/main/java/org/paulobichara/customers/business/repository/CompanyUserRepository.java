package org.paulobichara.customers.business.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.paulobichara.customers.domain.entity.CompanyUser;

/**
 * Class that represents a CompanyUser repository
 * @author Paulo Augusto Dacach Bichara
 *
 */
@Stateless
public class CompanyUserRepository 
{
	
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Method that finds a specific company user by its unique id
	 * @param id company user unique id
	 * @return
	 */
	public CompanyUser findCompanyUser(Long id) 
	{
		return this.entityManager.find(CompanyUser.class, id);
	}
	
	/**
	 * Method that adds or updates a company user
	 * @param user company user
	 * @return persisted company user
	 */
	public CompanyUser addOrUpdateCompanyUser(CompanyUser user) 
	{
		if (user.getId() == null) 
		{
			this.entityManager.persist(user);			
		}
		else 
		{
			user = this.entityManager.merge(user);
		}
		
		return user;
	}
	
	/**
	 * Method that removes a company user by its unique id
	 * @param id company user unique id
	 */
	public void removeCompanyUser(Long id) 
	{
		Query query = this.entityManager.createNamedQuery("CompanyUser.removeById");
		query.setParameter("prId", id);
		query.executeUpdate();
	}
	
	private void addFilterToCriteriaQuery(final CriteriaQuery<?> criteriaQuery, final String filter)
	{
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		Root<CompanyUser> user = criteriaQuery.from(CompanyUser.class);
		
		if (filter != null && !filter.trim().equals("")) 
		{
			Expression<String> completeName = criteriaBuilder.concat(user.<String>get("firstName"), " ");
			completeName = criteriaBuilder.concat(completeName, user.<String>get("lastName"));
			
			criteriaQuery.where(criteriaBuilder.like(completeName, "%"+ filter +"%"));
		}
	}
	
	/**
	 * Method that returns the number of filtered users
	 * @param filter filter to be applied on the count operation
	 * @return number of filtered users
	 */
	public long countCompanyUsers(final String filter)
	{
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		this.addFilterToCriteriaQuery(criteriaQuery, filter);
		criteriaQuery.select(criteriaBuilder.count(criteriaQuery.getRoots().iterator().next()));
		
		return this.entityManager.createQuery(criteriaQuery).getSingleResult();
	}
	
	/**
	 * Method that returns users from the database based on the provided parameters
	 * 
	 * @param filter a filter string to be applied to the user database
	 * @param currentPage the current page of the search (pagination parameter)
	 * @param resultsPerPage the number of records to be returned (pagination parameter)
	 * @param sortField the field to be used as the sort criteria
	 * @param sortAscending parameter indicating if the sort is ascending or descending
	 * @return list of filtered users
	 */
	@SuppressWarnings("unchecked")
	public List<CompanyUser> getCompanyUsers(
				final String filter, 
				final int currentPage, 
				final int resultsPerPage,
				final String sortField,
				final boolean sortAscending)
	{
		CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<CompanyUser> criteriaQuery = criteriaBuilder.createQuery(CompanyUser.class);
		this.addFilterToCriteriaQuery(criteriaQuery, filter);
		Root<CompanyUser> root = (Root<CompanyUser>)criteriaQuery.getRoots().iterator().next();
		criteriaQuery.select(root);

		if (sortField != null && !sortField.trim().equals(""))
		{
			if (sortAscending)
			{
				criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sortField)));
			}
			else
			{
				criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sortField)));
			}
		}		
		
		Query query = this.entityManager.createQuery(criteriaQuery);
		
		if (resultsPerPage > 0)
		{
			query.setMaxResults(resultsPerPage);
			
			if (currentPage > 0)
			{
				query.setFirstResult(currentPage * resultsPerPage);
			}
		}
		
		return query.getResultList();
	}

}
