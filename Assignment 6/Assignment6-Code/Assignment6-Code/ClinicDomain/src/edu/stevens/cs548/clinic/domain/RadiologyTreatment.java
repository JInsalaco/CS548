package edu.stevens.cs548.clinic.domain;

import java.util.Date;
import java.util.List;

//TODO JPA annotations

public class RadiologyTreatment extends Treatment {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3656673416179492428L;

	// TODO JPA annotation
	protected List<Date> treatmentDates;

	public List<Date> getTreatmentDates() {
		return treatmentDates;
	}

	public void setTreatmentDates(List<Date> treatmentDates) {
		this.treatmentDates = treatmentDates;
	}

	@Override
	public <T> T export(ITreatmentExporter<T> visitor) {
		// TODO
		return null;
	}
	
}
