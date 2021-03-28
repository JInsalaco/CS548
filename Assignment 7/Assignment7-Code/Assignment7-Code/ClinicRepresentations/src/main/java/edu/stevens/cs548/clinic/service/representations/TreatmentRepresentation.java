package edu.stevens.cs548.clinic.service.representations;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;

import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.TreatmentDtoFactory;
import edu.stevens.cs548.clinic.service.web.rest.data.ObjectFactory;
import edu.stevens.cs548.clinic.service.web.rest.data.TreatmentType;
import edu.stevens.cs548.clinic.service.web.rest.data.dap.LinkType;

@XmlRootElement
public class TreatmentRepresentation extends TreatmentType {
	
	private ObjectFactory repFactory = new ObjectFactory();
	
	public LinkType getLinkPatient() {
		return this.getPatient();
	}
	
	public LinkType getLinkProvider() {
		return this.getProvider();
	}
	
	public static LinkType getTreatmentLink(long tid, UriInfo uriInfo) {
		UriBuilder ub = uriInfo.getBaseUriBuilder();
		ub.path("treatment");
		UriBuilder ubTreatment = ub.clone().path("{tid}");
		String treatmentURI = ubTreatment.build(Long.toString(tid)).toString();
	
		LinkType link = new LinkType();
		link.setUrl(treatmentURI);
		link.setRelation(Representation.RELATION_TREATMENT);
		link.setMediaType(Representation.MEDIA_TYPE);
		return link;
	}
	
	private TreatmentDtoFactory treatmentDtoFactory;
	
	public TreatmentRepresentation() {
		super();
		treatmentDtoFactory = new TreatmentDtoFactory();
	}
	
	public TreatmentRepresentation (TreatmentDto dto, UriInfo uriInfo) {
		this();
		this.id = getTreatmentLink(dto.getId(), uriInfo);
		this.patient =  PatientRepresentation.getPatientLink(dto.getPatient(), uriInfo);
		this.provider = ProviderRepresentation.getProviderLink(dto.getProvider(), uriInfo);
		if (true) throw new IllegalStateException("Unimplemented treatment provider");
		
		this.diagnosis = dto.getDiagnosis();
		
		if (dto.getDrugTreatment() != null) {
			this.drugTreatment = repFactory.createDrugTreatmentType();
			this.drugTreatment.setName(dto.getDrugTreatment().getDrug());
			this.drugTreatment.setDosage(dto.getDrugTreatment().getDosage());
		} else if (dto.getRadiologyTreatment() != null){
			this.radiology = repFactory.createRadiologyType();
			this.radiology.getDate().addAll(dto.getRadiologyTreatment().getTreatmentDates());
		} else if (dto.getSurgeryTreatment() != null) {
			this.surgery = repFactory.createSurgeryType();
			this.surgery.setDate(dto.getSurgeryTreatment().getTreatmentDates());
		}
	}

	public TreatmentDto getTreatment() {
		TreatmentDto m = null;
		if (this.getDrugTreatment() != null) {
			m = treatmentDtoFactory.createDrugTreatmentDto();
			m.setId(Representation.getId(id));
			m.setPatient(Representation.getId(patient));
			m.setProvider(Representation.getId(provider));
			m.setDiagnosis(diagnosis);
			m.getDrugTreatment().setDrug(drugTreatment.getName());
			m.getDrugTreatment().setDosage(drugTreatment.getDosage());
		} else if (this.getSurgery() != null) {
			m = treatmentDtoFactory.createSurgeryTreatmentDto();
			m.setId(Representation.getId(id));
			m.setPatient(Representation.getId(patient));
			m.setProvider(Representation.getId(provider));
			m.setDiagnosis(diagnosis);
			m.getSurgeryTreatment().getTreatmentDates();
		} else if (this.getRadiology() != null) {
			m = treatmentDtoFactory.createRadiologyTreatmentDto();
			m.setId(Representation.getId(id));
			m.setPatient(Representation.getId(patient));
			m.setProvider(Representation.getId(provider));
			m.setDiagnosis(diagnosis);
			m.getRadiologyTreatment().getTreatmentDates();
		}
		return m;
	}

	
}
