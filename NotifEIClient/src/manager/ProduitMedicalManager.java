package manager;
 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.*;

public class ProduitMedicalManager implements ProduitMedicalService {
	
	private EntityManagerFactory emf = null;

    public ProduitMedicalManager() { 
    	
    }
    
    @Override
    public void init() {
	    emf = Persistence.createEntityManagerFactory("jpaExtends");
    }
    
    @Override
    public void close() {
    	if (emf != null) {
    		emf.close();
    	}
    } 

	@Override
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
	
	@Override
	public ProduitMedical retrieveById(int id) {
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
	@Override
	public List<ProduitMedical> retrieveByName(String nom) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select p from ProduitMedical p where p.nom like '" + nom + "%'";
			Query query = em.createQuery(requete);
			List<ProduitMedical> list = (List<ProduitMedical>) query.getResultList();
			return list;
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProduitMedical> retrieveAll() {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select p from ProduitMedical p ";
			Query query = em.createQuery(requete);
			List<ProduitMedical> produitsMedicaux = query.getResultList();
		    return produitsMedicaux;
		} finally {
			em.close();
		}
	}
	
	@Override
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
	
	@Override
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
