package manager;
 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.*;

public class CentreManager implements CentreService {
	
	private EntityManagerFactory emf = null;

    public CentreManager() { 
    	
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
	
	@Override
	public Centre retrieveById(int id) {
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
	@Override
	public Centre retrieveForConnexion(String login, String password) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select c from Centre c "
					+ "where c.login='" + login 
					+ "' and c.password='" + password + "'";
			Query query = em.createQuery(requete);
			List<Centre> list = (List<Centre>) query.getResultList();
			if (list.size() == 1) {
				Centre centre = list.get(0);
		    	return centre;
			} else {
				return null;
			}
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Centre retrieveByLogin(String login) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select c from Centre c "
					+ "where c.login='" + login + "'";
			Query query = em.createQuery(requete);
			List<Centre> list = (List<Centre>) query.getResultList();
			if (list.size() == 1) {
				Centre centre = list.get(0);
		    	return centre;
			} else {
				return null;
			}
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Centre> retrieveByName(String nom) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select c from Centre c "
					+ "where c.nom like '" + nom + "%'";
			Query query = em.createQuery(requete);
			List<Centre> centres = query.getResultList();
		    return centres;
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Centre> retrieveAll() {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select c from Centre c ";
			Query query = em.createQuery(requete);
			List<Centre> centres = query.getResultList();
		    return centres;
		} finally {
			em.close();
		}
	}
	
	@Override
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
	
	@Override
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
