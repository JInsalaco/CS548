package edu.stevens.cs548.clinic.domain;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ProviderDAO implements IProviderDAO {
	@PersistenceContext
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
		long npi = provider.getNpi();
		Query query = em.createNamedQuery("CountProviderByNPI").setParameter("npi", npi);
		Long numExisting = (Long) query.getSingleResult();
		
		if (numExisting < 1) {
			// Add to database (and sync with database to generate primary key)
			// Don't forget to initialize the patient aggregate with a treatment DAO
			em.persist(provider);
			em.flush();
			provider.setTreatmentDAO(this.treatmentDAO);
			
			return provider.getId();
		} else {
			throw new ProviderExn("Insertion: Provider with npi (" + npi + ") already exists.");
		}
	}

	@Override
	public Provider getProvider(long id) throws ProviderExn {
		
		/*
		 * Retrieve patient using primary key
		 */
		Provider p = em.find(Provider.class, id);
		if (p == null) {
			throw new ProviderExn("Provider not found: primary key = " + id);
		} else {
			p.setTreatmentDAO(this.treatmentDAO);
			return p;
		}
	}

	@Override
	public Provider getProviderByNPI(long pid) throws ProviderExn {
		TypedQuery<Provider> query = em.createNamedQuery("SearchProviderByNPI", Provider.class).setParameter("NPI",pid);
		List<Provider> providers = query.getResultList();
		
		if (providers.size() > 1) {
			throw new ProviderExn("Duplicate provider records: npi = " + pid);
		} else if (providers.size() < 1) {
			throw new ProviderExn("Patient not found: patient id = " + pid);
		} else {
			Provider p = providers.get(0);
			p.setTreatmentDAO(this.treatmentDAO);
			return p;
		}
	}
	
	@Override
	public void deleteProviders() {
		Query update = em.createNamedQuery("RemoveAllPatients");
		update.executeUpdate();
	}

}
