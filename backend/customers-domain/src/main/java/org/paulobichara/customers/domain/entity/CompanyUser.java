package org.paulobichara.customers.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Class that represents an user from a customer company
 * @author Paulo Augusto Dacach Bichara
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "CompanyUser.listAll", query = "Select c from CompanyUser c"),
	@NamedQuery(name = "CompanyUser.listSimilar", query = "Select c from CompanyUser c where c.id <> :prId and c.company.id = :prCompanyId and (c.firstName = :prFirstName or c.lastName = :prLastName)"),	
	@NamedQuery(name = "CompanyUser.countAll", query = "Select count(c) from CompanyUser c"),
	@NamedQuery(name = "CompanyUser.removeById", query = "Delete from CompanyUser c where c.id = :prId"),
	@NamedQuery(name = "CompanyUser.removeByCompanyId", query = "Delete from CompanyUser c where c.company.id = :prCompanyId")
})
public class CompanyUser implements Serializable 
{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;

	@Column(nullable = true)
	private String role;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId")
	private CustomerCompany company;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	/**
	 * Method that returns the unique identifier
	 * @return unique identifier
	 */
	public Long getId() 
	{
		return id;
	}

	/**
	 * Method that sets the unique identifier
	 * @param id unique identifier
	 */
	public void setId(Long id) 
	{
		this.id = id;
	}

	/**
	 * Method that returns the first name
	 * @return first name
	 */
	public String getFirstName() 
	{
		return firstName;
	}

	/**
	 * Method that sets the first name
	 * @param firstName first name
	 */
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	/**
	 * Method that returns the last name
	 * @return last name
	 */
	public String getLastName() 
	{
		return lastName;
	}

	/**
	 * Method that sets the last name
	 * @param lastName last name
	 */
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	/**
	 * Method that returns the role in company
	 * @return role in company
	 */
	public String getRole() 
	{
		return role;
	}

	/**
	 * Method that sets the role of the user in the company he works for
	 * @param role role of the user in the company he works for
	 */
	public void setRole(String role) 
	{
		this.role = role;
	}

	/**
	 * Method that returns the company
	 * @return company that the user works for
	 */
	public CustomerCompany getCompany() 
	{
		return company;
	}

	/**
	 * Method that sets the company that the user works for
	 * @param company company that the user works for
	 */
	public void setCompany(CustomerCompany company) 
	{
		this.company = company;
	}

	/**
	 * Method that returns the email
	 * @return email
	 */
	public String getEmail() 
	{
		return email;
	}

	/**
	 * Method that sets the email
	 * @param email email
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
	
}
