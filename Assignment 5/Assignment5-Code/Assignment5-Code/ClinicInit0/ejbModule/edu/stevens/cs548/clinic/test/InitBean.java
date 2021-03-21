package edu.stevens.cs548.clinic.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.stevens.cs548.clinic.domain.DrugTreatment;
import edu.stevens.cs548.clinic.domain.IPatientDAO;
import edu.stevens.cs548.clinic.domain.IPatientDAO.PatientExn;
import edu.stevens.cs548.clinic.domain.IProviderDAO;
import edu.stevens.cs548.clinic.domain.IProviderDAO.ProviderExn;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;
import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.domain.PatientDAO;
import edu.stevens.cs548.clinic.domain.PatientFactory;
import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.domain.Provider.ProviderType;
import edu.stevens.cs548.clinic.domain.ProviderDAO;
import edu.stevens.cs548.clinic.domain.ProviderFactory;
import edu.stevens.cs548.clinic.domain.SurgeryTreatment;
import edu.stevens.cs548.clinic.domain.Treatment;
import edu.stevens.cs548.clinic.domain.TreatmentDAO;
import edu.stevens.cs548.clinic.domain.TreatmentFactory;

/**
 * Session Bean implementation class TestBean
 */
@Singleton
@LocalBean
@Startup
public class InitBean {

	private static Logger logger = Logger.getLogger(InitBean.class.getCanonicalName());

	/**
	 * Default constructor.
	 */
	public InitBean() {
	}
	
	@PersistenceContext(unitName = "ClinicDomain")
	EntityManager em;

	@PostConstruct
	private void init() {
		/*
		 * Put your testing logic here. Use the logger to display testing output in the server logs.
		 */
		logger.info("Joseph Insalaco: ");

		try {
			Date date = new Date();
			Date date1 = new Date();
			List<Date> td = new ArrayList<Date>();
			td.add(date);
			td.add(date1);
			IPatientDAO patientDAO = new PatientDAO(em);
			IProviderDAO providerDAO = new ProviderDAO(em);
			ITreatmentDAO treatmentDAO = new TreatmentDAO(em);

			PatientFactory patientFactory = new PatientFactory();
			ProviderFactory providerFactory = new ProviderFactory();
			TreatmentFactory treatmentFactory = new TreatmentFactory();
			
			/*
			 * Clear the database and populate with fresh data.
			 * 
			 * If we ensure that deletion of patients cascades deletes of treatments,
			 * then we only need to delete patients.
			 */
			patientDAO.deletePatients();
			providerDAO.deleteProviders();
			
			Patient john = patientFactory.createPatient(12345678L, "John Doe");
			patientDAO.addPatient(john);
			logger.info("Added "+john.getName()+" with id "+john.getId());
			
			Patient db = patientFactory.createPatient(123456789L, "DaBaby");
			patientDAO.addPatient(db);
			logger.info("Added "+db.getName()+" with id "+db.getId());
			
			Patient bb = patientFactory.createPatient(123L, "Bob Barker");
			patientDAO.addPatient(bb);
			logger.info("Added "+bb.getName()+" with id "+bb.getId());
			
			Provider dr = providerFactory.createProvider(95L, "Dr John 2", ProviderType.INTERNIST);
			providerDAO.addProvider(dr);
			logger.info("Added "+dr.getName()+" with NPI "+dr.getNpi());
			
			Treatment dt = treatmentFactory.createDrugTreatment("Cancer", "Amoxicillin", 1.3f);
			Treatment st = treatmentFactory.createSurgeryTreatment("Hepatitis", date);
			Treatment rt = treatmentFactory.createRadiologyTreatment("Lung Cancer", td);
			Treatment dt1 = treatmentFactory.createDrugTreatment("Super Cancer", "Chemo", 50.5f);
			
			dr.addTreatment(john, dt);
			dr.addTreatment(db, st);
			dr.addTreatment(bb, rt);
			dr.addTreatment(john, dt1);
			
			Patient j = patientDAO.getPatient(john.getId());
			Patient j1 = patientDAO.getPatientByPatientId(j.getPatientId());
			List<Long> tid = john.getTreatmentIds();

			for(int i = 0; i<tid.size(); i++) {
				logger.info("Treatments for: "+john.getName()+" are of type: "+treatmentDAO.getTreatment(tid.get(i)));
			}
			
			
			
		} catch (PatientExn e) {

			// logger.log(Level.SEVERE, "Failed to add patient record.", e);
			IllegalStateException ex = new IllegalStateException("Failed to add patient record.");
			ex.initCause(e);
			throw ex;
			
		} catch (ProviderExn e) {
			IllegalStateException ex = new IllegalStateException("Failed to add provider record.");
			ex.initCause(e);
			throw ex;
			
		} catch (TreatmentExn e) {
			IllegalStateException ex = new IllegalStateException("Failed to get treatment");
			ex.initCause(e);
			throw ex;
		}
		
			
	}

}
