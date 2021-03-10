package edu.stevens.cs548.clinic.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// TODO
@Entity
@DiscriminatorValue("RA")
public class RadiologyTreatment extends Treatment {

	private static final long serialVersionUID = -3656673416179492428L;

	@ElementCollection
	@Temporal(TemporalType.DATE)
	protected Collection<Date> treatmentDates;

	public Collection<Date> getTreatmentDates() {
		return treatmentDates;
	}

	public void setTreatmentDates(Collection<Date> treatmentDates) {
		this.treatmentDates = treatmentDates;
	}
	
}
