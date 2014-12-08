package model;
 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PatientManager {

	private EntityManagerFactory emf = null;

    public PatientManager() { 
    	
    }
    
    public void init() {
	    emf = Persistence.createEntityManagerFactory("jpaExtends");
    }
    
    public void close() {
    	if (emf != null) {
    		emf.close();
    	}
    } 

	public void create(Patient patient) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();    
			EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    em.persist(patient);
		    transac.commit();   
		} finally {
			em.close(); 
		}
	}
	
	public Patient retrieve(int id) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			Patient patient = em.find(Patient.class, id);
		    return patient;
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Patient> retrieve(String champ, String valeur) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			Query query = em.createQuery("select p from Patient p where p." + champ + " = '" + valeur + "'");
			List<Patient> patients = query.getResultList();
			return patients;
		} finally {
			em.close();
		}
	}
	
	public void update(Patient patient) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
		    EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    Patient p = em.find(Patient.class, patient.getId());
		    if (p != null) {
		    	p.setNom(patient.getNom());
		    	p.setPrenom(patient.getPrenom());
		    	p.setDateNaissance(patient.getDateNaissance());
		    	p.setTelephone(patient.getTelephone());
		    	p.setEmail(patient.getEmail());
		    	p.setAdresse(patient.getAdresse());
		    	p.setVille(patient.getVille());
		    	p.setCodePostal(patient.getCodePostal());
		      	em.flush();
		    }
		    transac.commit();
		} finally {
			em.close();
		}
	}
	
	public void delete(int id) {
		EntityManager em = null;
		try {		
			em = emf.createEntityManager();
		    EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    Patient patient = em.find(Patient.class, id);    
		    if (patient != null) {
		      em.remove(patient);      
		    }
		    transac.commit();
		} finally {		    
			em.close();
		} 
	}

}