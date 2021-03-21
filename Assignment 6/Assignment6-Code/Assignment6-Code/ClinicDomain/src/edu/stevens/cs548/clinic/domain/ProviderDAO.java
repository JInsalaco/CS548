package edu.stevens.cs548.clinic.domain;

import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ProviderDAO implements IProviderDAO {

	private EntityManager em;
	private TreatmentDAO treatmentDAO;
	
	public ProviderDAO(EntityManager em) {
		this.em = em;
		this.treatmentDAO = new TreatmentDAO(em);
	}

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(ProviderDAO.class.getCanonicalName());

	@Override
	public long addProvider(Provider provider) throws ProviderExn {
		/*
		 * TODO add to database (and sync with database to generate primary key).
		 * Don't forget to initialize the Provider aggregate with a treatment DAO.
		 */
		return 0L;
	}

	@Override
	public Provider getProvider(long id) throws ProviderExn {
		
		/*
		 * TODO retrieve Provider using primary key
		 */
		return null;
	}

	@Override
	public Provider getProviderByNPI(long pid) throws ProviderExn {
		/*
		 * TODO retrieve Provider using query on Provider id (secondary key)
		 */
		return null;
	}
	
	@Override
	public void deleteProviders() {
		Query update = em.createNamedQuery("RemoveAllProviders");
		update.executeUpdate();
	}

}
