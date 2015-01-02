package manager;
 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.*;

public class ProduitMedicalManager {

	private EntityManagerFactory emf = null;

    public ProduitMedicalManager() { 
    	
    }
    
    public void init() {
	    emf = Persistence.createEntityManagerFactory("jpaExtends");
    }
    
    public void close() {
    	if (emf != null) {
    		emf.close();
    	}
    } 

	public void create(ProduitMedical pm) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();    
			EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    em.persist(pm);
		    transac.commit();   
		} finally {
			em.close(); 
		}
	}
	
	public ProduitMedical retrieve(int id) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			ProduitMedical pm = em.find(ProduitMedical.class, id);
		    return pm;
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<ProduitMedical> retrieve(String champ, String valeur) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			Query query = em.createQuery("select p from ProduitMedical p where p." + champ + " = '" + valeur + "'");
			List<ProduitMedical> pms = query.getResultList();
			return pms;
		} finally {
			em.close();
		}
	}
	
	public void update(ProduitMedical pm) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
		    EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    ProduitMedical p = em.find(ProduitMedical.class, pm.getId());
		    if (p != null) {
		    	
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
		    ProduitMedical pm = em.find(ProduitMedical.class, id);    
		    if (pm != null) {
		      em.remove(pm);      
		    }
		    transac.commit();
		} finally {		    
			em.close();
		} 
	}

}
