package model;
 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class LaboratoireManager {

	private EntityManagerFactory emf = null;

    public LaboratoireManager() { 
    	
    }
    
    public void init() {
	    emf = Persistence.createEntityManagerFactory("jpaExtends");
    }
    
    public void close() {
    	if (emf != null) {
    		emf.close();
    	}
    } 

	public void create(Laboratoire laboratoire) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();    
			EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    em.persist(laboratoire);
		    transac.commit();   
		} finally {
			em.close(); 
		}
	}
	
	public Laboratoire retrieve(int id) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			Laboratoire laboratoire = em.find(Laboratoire.class, id);
		    return laboratoire;
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Laboratoire> retrieve(String champ, String valeur) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			Query query = em.createQuery("select l from Laboratoire l where l." + champ + " = '" + valeur + "'");
			List<Laboratoire> laboratoires = query.getResultList();
			return laboratoires;
		} finally {
			em.close();
		}
	}
	
	public void update(Laboratoire laboratoire) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
		    EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    Laboratoire l = em.find(Laboratoire.class, laboratoire.getId());
		    if (l != null) {
		    	l.setNom(laboratoire.getNom());
		    	l.setTelephone(laboratoire.getTelephone());
		    	l.setEmail(laboratoire.getEmail());
		    	l.setAdresse(laboratoire.getAdresse());
		    	l.setVille(laboratoire.getVille());
		    	l.setCodePostal(laboratoire.getCodePostal());
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
		    Laboratoire laboratoire = em.find(Laboratoire.class, id);    
		    if (laboratoire != null) {
		      em.remove(laboratoire);      
		    }
		    transac.commit();
		} finally {		    
			em.close();
		} 
	}

}
