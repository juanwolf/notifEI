package model;
 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EffetIndesirableManager {

	private EntityManagerFactory emf = null;

    public EffetIndesirableManager() { 
    	
    }
    
    public void init() {
	    emf = Persistence.createEntityManagerFactory("jpaExtends");
    }
    
    public void close() {
    	if (emf != null) {
    		emf.close();
    	}
    } 

	public void create(EffetIndesirable ei) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();    
			EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    em.persist(ei);
		    transac.commit();   
		} finally {
			em.close(); 
		}
	}
	
	public EffetIndesirable retrieve(int id) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			EffetIndesirable ei = em.find(EffetIndesirable.class, id);
		    return ei;
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<EffetIndesirable> retrieve(String champ, String valeur) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			Query query = em.createQuery("select e from EffetIndesirable e where e." + champ + " = '" + valeur + "'");
			List<EffetIndesirable> eis = query.getResultList();
			return eis;
		} finally {
			em.close();
		}
	}
	
	public void update(EffetIndesirable ei) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
		    EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    EffetIndesirable e = em.find(EffetIndesirable.class, ei.getId());
		    if (e != null) {
		    	
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
		    EffetIndesirable ei = em.find(EffetIndesirable.class, id);    
		    if (ei != null) {
		      em.remove(ei);      
		    }
		    transac.commit();
		} finally {		    
			em.close();
		} 
	}

}
