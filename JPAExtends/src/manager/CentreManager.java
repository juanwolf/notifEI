package manager;
 
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.*;

public class CentreManager {

	private EntityManagerFactory emf = null;

    public CentreManager() { 
    	
    }
    
    public void init() {
	    emf = Persistence.createEntityManagerFactory("jpaExtends");
    }
    
    public void close() {
    	if (emf != null) {
    		emf.close();
    	}
    } 

	public void create(Centre centre) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();    
			EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    em.persist(centre);
		    transac.commit();   
		} finally {
			em.close(); 
		}
	}
	
	public Centre retrieve(int id) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			Centre centre = em.find(Centre.class, id);
		    return centre;
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Centre> retrieve(String champ, String valeur) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			Query query = em.createQuery("select c from Centre c where c." + champ + " = '" + valeur + "'");
			List<Centre> centres = query.getResultList();
			return centres;
		} finally {
			em.close();
		}
	}
	
	public void update(Centre centre) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
		    EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    Centre c = em.find(Centre.class, centre.getId());
		    if (c != null) {
		    	c.setNom(centre.getNom());
		    	c.setTelephone(centre.getTelephone());
		    	c.setEmail(centre.getEmail());
		    	c.setAdresse(centre.getAdresse());
		    	c.setVille(centre.getVille());
		    	c.setCodePostal(centre.getCodePostal());
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
		    Centre centre = em.find(Centre.class, id);    
		    if (centre != null) {
		      em.remove(centre);      
		    }
		    transac.commit();
		} finally {		    
			em.close();
		} 
	}

}
