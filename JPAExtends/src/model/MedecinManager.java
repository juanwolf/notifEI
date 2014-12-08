package model;
 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MedecinManager {

	private EntityManagerFactory emf = null;

    public MedecinManager() { 
    	
    }
    
    public void init() {
	    emf = Persistence.createEntityManagerFactory("jpaExtends");
    }
    
    public void close() {
    	if (emf != null) {
    		emf.close();
    	}
    } 

	public void create(Medecin medecin) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();    
			EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    em.persist(medecin);
		    transac.commit();   
		} finally {
			em.close(); 
		}
	}
	
	public Medecin retrieve(int id) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			Medecin medecin = em.find(Medecin.class, id);
		    return medecin;
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Medecin> retrieve(String champ, String valeur) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			Query query = em.createQuery("select m from Medecin m where m." + champ + " = '" + valeur + "'");
			List<Medecin> medecins = query.getResultList();
			return medecins;
		} finally {
			em.close();
		}
	}
	
	public void update(Medecin medecin) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
		    EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    Medecin m = em.find(Medecin.class, medecin.getId());
		    if (m != null) {
		    	m.setNom(medecin.getNom());
		    	m.setPrenom(medecin.getPrenom());
		    	m.setTelephone(medecin.getTelephone());
		    	m.setEmail(medecin.getEmail());
		    	m.setAdresse(medecin.getAdresse());
		    	m.setVille(medecin.getVille());
		    	m.setCodePostal(medecin.getCodePostal());
		    	m.setQualification(medecin.getQualification());
		    	m.setLieuTravail(medecin.getLieuTravail());
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
		    Medecin medecin = em.find(Medecin.class, id);    
		    if (medecin != null) {
		      em.remove(medecin);      
		    }
		    transac.commit();
		} finally {		    
			em.close();
		} 
	}

}
