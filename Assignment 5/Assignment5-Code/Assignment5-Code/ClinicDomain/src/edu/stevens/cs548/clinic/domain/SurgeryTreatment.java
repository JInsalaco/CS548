package edu.stevens.cs548.clinic.domain;

import java.util.Date;

import javax.persistence.*;

@Entity
@DiscriminatorValue("SU")
public class SurgeryTreatment extends Treatment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4173146640306267418L;
	
	@Temporal(TemporalType.DATE)
	private Date surgeryDate;

	public Date getSurgeryDate() {
		return surgeryDate;
	}

	public void setSurgeryDate(Date surgeryDate) {
		this.surgeryDate = surgeryDate;
	}

	@Override
	public <T> T export(ITreatmentExporter<T> visitor) {
		return visitor.exportSurgery (this.getId(),
				   this.getPatient().getId(),
				   this.getProvider().getId(),
		   		   this.getDiagnosis(),
		   		   this.surgeryDate);
	}

}
