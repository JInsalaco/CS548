package edu.stevens.cs548.clinic.domain;

import java.util.Date;

import javax.persistence.Entity;

//TODO JPA annotations
public class SurgeryTreatment extends Treatment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4173146640306267418L;
	
	private Date surgeryDate;

	public Date getSurgeryDate() {
		return surgeryDate;
	}

	public void setSurgeryDate(Date surgeryDate) {
		this.surgeryDate = surgeryDate;
	}

	@Override
	public <T> T export(ITreatmentExporter<T> visitor) {
		// TODO
		return null;
	}

}
