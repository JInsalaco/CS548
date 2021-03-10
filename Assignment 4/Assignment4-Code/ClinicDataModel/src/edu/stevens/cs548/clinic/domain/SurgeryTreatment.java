package edu.stevens.cs548.clinic.domain;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

@Entity
@DiscriminatorValue("SU")
public class SurgeryTreatment extends Treatment {

	private static final long serialVersionUID = 4173146640306267418L;
	
	@Temporal(DATE)
	private Date surgeryDate;

	public Date getSurgeryDate() {
		return surgeryDate;
	}

	public void setSurgeryDate(Date surgeryDate) {
		this.surgeryDate = surgeryDate;
	}

}
