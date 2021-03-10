package edu.stevens.cs548.clinic.service.dto.test;

import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.RadiologyTreatmentType;
import edu.stevens.cs548.clinic.service.dto.SurgeryTreatmentType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.DateAdapter;
import edu.stevens.cs548.clinic.service.dto.util.PatientDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.ProviderDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.TreatmentDtoFactory;

public class SchemaTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("** Testing with choice element start");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("** Testing with choice element end");
	}

	@Test
	public void test() {
		PatientDtoFactory patientFactory = new PatientDtoFactory();
		ProviderDtoFactory providerFactory = new ProviderDtoFactory();
		TreatmentDtoFactory treatmentFactory = new TreatmentDtoFactory();

	    try {
		    JAXBContext context = JAXBContext.newInstance("edu.stevens.cs548.clinic.service.dto");
		    Marshaller marshaller = context.createMarshaller();
		    marshaller.setProperty("jaxb.formatted.output",Boolean.TRUE);

		    //PatientDto
		    PatientDto patient = patientFactory.createPatientDto();
		    patient.setId(001);
		    patient.setPatientId(001);
		    patient.setName("Joe");
		    	
		    System.out.println();
			marshaller.marshal(patient, System.out);

			//ProviderDto
		    ProviderDto provider = providerFactory.createProviderDto();
		    provider.setId(1001);
		    provider.setName("Test");
		    provider.setNpi("NPI");
		    
		    System.out.println();
			marshaller.marshal(provider,System.out);

			//Drug TreatmentDto
			TreatmentDto treatment = treatmentFactory.createDrugTreatmentDto();
			DrugTreatmentType dt = new DrugTreatmentType();
			dt.setDosage(1.1);
			dt.setDrug("tamiflu");
			treatment.setDiagnosis("Cold");
			treatment.setId(101);
			treatment.setPatient(1010101);
			treatment.setProvider(1001);
			treatment.setDrugTreatment(dt);
			
			System.out.println();
			marshaller.marshal(treatment,System.out);
			
			//Surgery TreatmentDto
			TreatmentDto treatment1 = treatmentFactory.createSurgeryTreatmentDto();
			SurgeryTreatmentType st = new SurgeryTreatmentType();
			Date mydate = new Date();
			st.setDateOfSurgery(mydate);
			treatment1.setDiagnosis("Fractured Pelvis");
			treatment1.setId(102);
			treatment1.setPatient(1010101);
			treatment1.setProvider(1001);
			treatment1.setSurgeryTreatment(st);
			
			System.out.println();
			marshaller.marshal(treatment1,System.out);
			
			//Radiology TreatmentDto
			TreatmentDto treatment2 = treatmentFactory.createRadiologyTreatmentDto();
			RadiologyTreatmentType rt = new RadiologyTreatmentType();
			Date mydate1 = new Date();
			Date mydate2 = new Date();
			rt.getDateOfTreatment().add(mydate1);
			rt.getDateOfTreatment().add(mydate2);
			treatment2.setDiagnosis("Cancer");
			treatment2.setId(103);
			treatment2.setPatient(1010101);
			treatment2.setProvider(1001);
			treatment2.setRadiologyTreatment(rt);
			
			System.out.println();
			marshaller.marshal(treatment2,System.out);
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
