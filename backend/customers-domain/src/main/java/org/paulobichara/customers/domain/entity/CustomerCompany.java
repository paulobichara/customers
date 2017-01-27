package org.paulobichara.customers.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Class that represents a customer company
 * @author Paulo Augusto Dacach Bichara
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "CustomerCompany.listAll", query = "Select c from CustomerCompany c"),	
	@NamedQuery(name = "CustomerCompany.findByName", query = "Select c from CustomerCompany c where c.name = :prName"),
	@NamedQuery(name = "CustomerCompany.removeById", query = "Delete from CustomerCompany c where c.id = :prId")
})
public class CustomerCompany 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = true)
	private String description;
	
	/**
	 * Method that return the company unique id
	 * @return company unique id
	 */
	public Long getId() 
	{
		return id;
	}

	/**
	 * Method that sets the company unique id
	 * @param id company unique id
	 */
	public void setId(Long id) 
	{
		this.id = id;
	}

	/**
	 * Method that return the company name
	 * @return company name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * Method that sets the company name
	 * @param name company name
	 */
	public void setName(String name) 
	{
		this.name = name;
	}

	/**
	 * Method that return the company description
	 * @return company description
	 */
	public String getDescription() 
	{
		return description;
	}

	/**
	 * Method that sets the company description
	 * @param description
	 */
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
}
