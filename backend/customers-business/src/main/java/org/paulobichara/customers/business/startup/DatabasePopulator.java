package org.paulobichara.customers.business.startup;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.paulobichara.customers.business.repository.CustomerCompanyRepository;
import org.paulobichara.customers.domain.entity.CompanyUser;
import org.paulobichara.customers.domain.entity.CustomerCompany;

/**
 * Bean that initialize the in-memory database with the sample data present in companies-sample.csv and company-users-sample.csv
 * @author Paulo Augusto Dacach Bichara
 *
 */
@Singleton
@Startup
public class DatabasePopulator {

	/* Path to the sample customer companies resource file */
	private static final String CUSTOMERS_COMPANIES_SAMPLE = "/companies-sample.csv";

	/* Path to the sample company users resource file */
	private static final String COMPANY_USERS_SAMPLE = "/company-users-sample.csv";	

	/* Token separator used in the CSV resource file's format */
	private static final String CSV_TOKEN_SEPARATOR = ";";		

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private CustomerCompanyRepository companiesRepo;

	/**
	 * Method that initializes the database.
	 */
	@PostConstruct
	public void init()
	{
		try 
		{
			this.populateSampleCustomerCompanies();
			this.populateSampleCompanyUsers();
		} 
		catch ( URISyntaxException | IOException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Method that clears the database.
	 */
	@PreDestroy
	public void finalize() 
	{
		this.entityManager.createQuery("delete from CompanyUser c").executeUpdate();		
		this.entityManager.createQuery("delete from CustomerCompany c").executeUpdate();
	}

	private void populateSampleCustomerCompanies() throws IOException 
	{
		BufferedReader bufferedReader = createBufferedReader(CUSTOMERS_COMPANIES_SAMPLE);
		
		try 
		{
			CustomerCompany company = null;
			String line = null;
			String[] fields = null;

			while ((line = bufferedReader.readLine()) != null) 
			{
				fields = line.split(CSV_TOKEN_SEPARATOR);
				company = new CustomerCompany();
				company.setName(fields[1]);
				company.setDescription(fields[2]);
				this.entityManager.persist(company);
			}
		}
		finally 
		{
			close(bufferedReader);
		}
	}

	private void populateSampleCompanyUsers() throws URISyntaxException, IOException 
	{
		BufferedReader bufferedReader = createBufferedReader(COMPANY_USERS_SAMPLE);
		try 
		{
			CompanyUser user = null;
			CustomerCompany company = null;
			String line = null;
			String[] fields = null;

			while ((line = bufferedReader.readLine()) != null) 
			{
				fields = line.split(CSV_TOKEN_SEPARATOR);
				user = new CompanyUser();
				user.setFirstName(fields[0]);
				user.setLastName(fields[1]);
				user.setRole(fields[2]);
				user.setEmail(fields[3]);
				company = this.companiesRepo.findCustomerCompany(fields[4]);
				user.setCompany(company);
				this.entityManager.persist(user);
			}
		} 
		finally 
		{
			close(bufferedReader);
		}
	}	
	
	public static BufferedReader createBufferedReader(String srcFile)
	{
			return new BufferedReader(new InputStreamReader(DatabasePopulator.class.getResourceAsStream(srcFile)));
	}
	
	public static void close(Closeable closeable)
	{
		try
		{
			closeable.close();
		}
		catch (IOException e) 
		{
			// Ignore exception
		}
	}	

}
