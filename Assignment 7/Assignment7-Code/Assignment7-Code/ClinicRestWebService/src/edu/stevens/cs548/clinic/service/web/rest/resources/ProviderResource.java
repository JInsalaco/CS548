package edu.stevens.cs548.clinic.service.web.rest.resources;

import java.net.URI;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.PatientDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.TreatmentDtoFactory;
import edu.stevens.cs548.clinic.service.ejb.IProviderService;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;
import edu.stevens.cs548.clinic.service.representations.PatientRepresentation;
import edu.stevens.cs548.clinic.service.representations.ProviderRepresentation;
import edu.stevens.cs548.clinic.service.representations.RepresentationFactory;
import edu.stevens.cs548.clinic.service.representations.TreatmentRepresentation;

@Path("/provider")
@RequestScoped
public class ProviderResource {
	
	final static Logger logger = Logger.getLogger(ProviderResource.class.getCanonicalName());
	
    @Context
    private UriInfo uriInfo;
    
    private RepresentationFactory factory = new RepresentationFactory();
    
    /**
     * Default constructor. 
     */
    public ProviderResource() {
    }
    
    @Inject
    private IProviderService providerService;

    @POST
    @Consumes("application/xml")
     public Response addProvider(ProviderRepresentation providerRep) {
    	try {
    		ProviderDto dto = providerRep.getProviderDto();
    		long id = providerService.addProvider(dto);
    		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path("{id}");
    		URI url = ub.build(Long.toString(id));
    		return Response.created(url).build();
    	} catch (ProviderServiceExn e) {
			throw new WebApplicationException(e.getMessage(), Response.Status.BAD_REQUEST);
    	}
    }
    
	/**
	 * Query methods for provider resources.
	 */
	@GET
	@Path("{id}")
	@Produces("application/xml")
	public ProviderRepresentation getProvider(@PathParam("id") String id) {
		try {
			long key = Long.parseLong(id);
			ProviderDto providerDTO = providerService.getProvider(key);
			ProviderRepresentation providerRep = factory.createProviderRepresentation(providerDTO, uriInfo);
			return providerRep;
		} catch (ProviderServiceExn e) {
			throw new WebApplicationException(e.getMessage(), Response.Status.NOT_FOUND);
		}
	}
    
	@GET
	@Path("byProviderNPI")
	@Produces("application/xml")
	public ProviderRepresentation getProviderByNpi(@QueryParam("npi") String npi) {
		try {
			long pnpi= Long.parseLong(npi);
			ProviderDto providerDTO = providerService.getProviderByNPI(pnpi);
			ProviderRepresentation providerRep = factory.createProviderRepresentation(providerDTO, uriInfo);
			return providerRep;
		} catch (ProviderServiceExn e) {
			throw new WebApplicationException(e.getMessage(), Response.Status.NOT_FOUND);
		}
	}
	
	// TODO complete including annotations
	@GET
	@Path("{id}/treatments")
	@Produces("application/xml")
	public Response addTreatment(@PathParam("id") String id, TreatmentRepresentation treatmentRep) {
    	try {
    		long key = Long.parseLong(id);
    		ProviderDto providerDto = providerService.getProvider(key);
    		
    		TreatmentDto dto = treatmentRep.getTreatment();

    		providerService.addTreatment(dto);
    			
    		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path("{id}");
    		URI url = ub.build(Long.toString(key));
    		return Response.created(url).build();

    	} catch (ProviderServiceExn e) {
    		throw new WebApplicationException(e.getMessage(), Response.Status.NOT_FOUND);
    	} catch (PatientServiceExn e) {
    		throw new WebApplicationException(e.getMessage(), Response.Status.NOT_FOUND);
    	}
	}
    

    @GET
    @Path("{id}/treatments/{tid}")
    @Produces("application/xml")
    public TreatmentRepresentation getProviderTreatment(@PathParam("id") String id, @PathParam("tid") String tid) {
    	try {
    		TreatmentDto treatment = providerService.getTreatment(Long.parseLong(id), Long.parseLong(tid)); 
    		TreatmentRepresentation treatmentRep = factory.createTreatmentRepresentation(treatment, uriInfo);
    		return treatmentRep;
    	} catch (ProviderServiceExn e) {
			throw new WebApplicationException(e.getMessage(), Response.Status.NOT_FOUND);
    	}
    }

}