package edu.stevens.cs548.clinic.service.dto.test;

import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
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
import edu.stevens.cs548.clinic.service.dto.util.PatientDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.ProviderDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.TreatmentDtoFactory;

public class SchemaTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("** Testing with element substitution start");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("** Testing with element substitution end");
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

		    PatientDto patient = patientFactory.createPatientDto();
		    patient.setId(1111);
		    patient.setName("Test Patient");
		    patient.setPatientId(1002);
		    
		    System.out.println();
			marshaller.marshal(patient,System.out);

		    ProviderDto provider = providerFactory.createProviderDto();
		    provider.setId(1111);
		    provider.setName("Test Patient");
		    provider.setNpi("Medtronic");
		    
		    
		    System.out.println();
			marshaller.marshal(provider,System.out);
			
			//DrugTreatmentType
			DrugTreatmentType drugTreatmentType = treatmentFactory.createDrugTreatmentType();
			JAXBElement<DrugTreatmentType> drugTreatment = treatmentFactory.createDrugTreatmentDto(drugTreatmentType);
			drugTreatmentType.setDrug("Chrome");
			drugTreatmentType.setDosage(11.2);

		    System.out.println();
			marshaller.marshal(drugTreatment,System.out);
			
			//Surgery Treatment Type
			SurgeryTreatmentType surgeryTreatmentType = treatmentFactory.createSurgeryTreatmentType();
			JAXBElement<SurgeryTreatmentType> surgeryTreatment = treatmentFactory.createSurgeryTreatmentDto(surgeryTreatmentType);
			Date date = new Date();
			surgeryTreatmentType.setDateOfSurgery(date);
			System.out.println();
			marshaller.marshal(surgeryTreatment, System.out);
			
			//Radiology Treatment Type
			RadiologyTreatmentType radiologyTreatmentType = treatmentFactory.createRadiologyTreatmentType();
			JAXBElement<RadiologyTreatmentType> radiologyTreatment = treatmentFactory.createRadiologyTreatmentDto(radiologyTreatmentType);
			Date date1 = new Date();
			Date date2 = new Date();
			radiologyTreatmentType.getDateOfRadiology().add(date1);
			radiologyTreatmentType.getDateOfRadiology().add(date2);
			System.out.println();
			marshaller.marshal(radiologyTreatment, System.out);

	    } catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
