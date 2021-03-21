package edu.stevens.cs548.clinic.service.dto.util;

import edu.stevens.cs548.clinic.domain.DrugTreatment;
import edu.stevens.cs548.clinic.domain.RadiologyTreatment;
import edu.stevens.cs548.clinic.domain.SurgeryTreatment;
import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.ObjectFactory;
import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.RadiologyTreatmentType;
import edu.stevens.cs548.clinic.service.dto.SurgeryTreatmentType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;

public class TreatmentDtoFactory {
	
	ObjectFactory factory;
	
	public TreatmentDtoFactory() {
		factory = new ObjectFactory();
	}
	
	public TreatmentDto createTreatmentDto () {
		return factory.createTreatmentDto();
	}
	
	public TreatmentDto createTreatmentDto (DrugTreatment d) {
		TreatmentDto t = factory.createTreatmentDto();
		DrugTreatmentType dt = factory.createDrugTreatmentType();
		dt.setDosage(d.getDosage());
		dt.setDrug(d.getDrug());
		t.setDiagnosis(d.getDiagnosis());
		t.setId(d.getId());
		t.setPatient(d.getPatient().getId());
		t.setProvider(d.getProvider().getNpi());
		t.setDrugTreatment(dt);
		return t;
	}
	
	public TreatmentDto createTreatmentDto (SurgeryTreatment d) {
		TreatmentDto t = factory.createTreatmentDto();
		SurgeryTreatmentType st = factory.createSurgeryTreatmentType();
		st.setTreatmentDates(d.getSurgeryDate());
		t.setDiagnosis(d.getDiagnosis());
		t.setId(d.getId());
		t.setPatient(d.getPatient().getId());
		t.setProvider(d.getProvider().getNpi());
		t.setSurgeryTreatment(st);
		return t;
	}
	
	public TreatmentDto createTreatmentDto (RadiologyTreatment d) {
		TreatmentDto t = factory.createTreatmentDto();
		RadiologyTreatmentType st = factory.createRadiologyTreatmentType();
		st.getTreatmentDates().addAll((d.getTreatmentDates()));
		t.setDiagnosis(d.getDiagnosis());
		t.setId(d.getId());
		t.setPatient(d.getPatient().getId());
		t.setProvider(d.getProvider().getNpi());
		return t;
	}
}
