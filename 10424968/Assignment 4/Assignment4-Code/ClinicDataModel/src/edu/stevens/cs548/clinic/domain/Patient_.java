package edu.stevens.cs548.clinic.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2021-03-07T13:39:51.516-0500")
@StaticMetamodel(Patient.class)
public class Patient_ {
	public static volatile SingularAttribute<Patient, Long> id;
	public static volatile ListAttribute<Patient, Treatment> treatments;
	public static volatile SingularAttribute<Patient, Long> patientId;
	public static volatile SingularAttribute<Patient, String> name;
}
