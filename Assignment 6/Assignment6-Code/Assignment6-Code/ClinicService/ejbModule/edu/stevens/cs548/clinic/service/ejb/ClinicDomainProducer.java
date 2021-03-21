package edu.stevens.cs548.clinic.service.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class PatientProducer
 */
@Stateless
@LocalBean
// @Dependent
public class ClinicDomainProducer {

    /**
     * Supports injection of entity manager. 
     */
    public ClinicDomainProducer() {
    }
    
    /**
     * TODO Use @PersistenceContext to inject a CONTAINER-managed entity manager.
     * 
     * See here for more discussion: https://stackoverflow.com/a/30007786
     */

    private EntityManager em;
    
    @Produces @ClinicDomain
    public EntityManager clinicDomainProducer() {
    	return em;
    }
    
    public void clinicDomainDispose(@Disposes @ClinicDomain EntityManager em) {
    	// Do NOT close a container-managed entity manager
    	// em.close();
    }

}
