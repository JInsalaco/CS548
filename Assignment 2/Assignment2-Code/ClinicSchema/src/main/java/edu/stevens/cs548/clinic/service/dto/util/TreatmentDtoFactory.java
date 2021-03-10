package edu.stevens.cs548.clinic.service.dto.util;

import edu.stevens.cs548.clinic.service.dto.ObjectFactory;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;

public class TreatmentDtoFactory {
	
	ObjectFactory factory;
	
	public TreatmentDtoFactory() {
		factory = new ObjectFactory();
	}
	
	public TreatmentDto createDrugTreatmentDto () {
		return factory.createTreatmentDto();
	}
	
	public TreatmentDto createSurgeryTreatmentDto () {
		return factory.createTreatmentDto();
	}
	
	public TreatmentDto createRadiologyTreatmentDto () {
		return factory.createTreatmentDto();
	}
}
