package manager;
 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.*;

public class LaboratoireManager implements LaboratoireService {

	private EntityManagerFactory emf = null;

    public LaboratoireManager() { 
    	
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
	
	@Override
	public Laboratoire retrieveById(int id) {
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
	@Override
	public Laboratoire retrieveForConnexion(String login, String password) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select l from Laboratoire l "
					+ "where l.login='" + login 
					+ "' and l.password='" + password + "'";
			Query query = em.createQuery(requete);
			List<Laboratoire> list = (List<Laboratoire>) query.getResultList();
			if (list.size() == 1) {
				Laboratoire laboratoire = list.get(0);
		    	return laboratoire;
			} else {
				return null;
			}
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Laboratoire retrieveByLogin(String login) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select l from Laboratoire l "
					+ "where l.login='" + login + "'";
			Query query = em.createQuery(requete);
			List<Laboratoire> list = (List<Laboratoire>) query.getResultList();
			if (list.size() == 1) {
				Laboratoire laboratoire = list.get(0);
		    	return laboratoire;
			} else {
				return null;
			}
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Laboratoire> retrieveAll() {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select l from Laboratoire l ";
			Query query = em.createQuery(requete);
			List<Laboratoire> laboratoires = query.getResultList();
		    return laboratoires;
		} finally {
			em.close();
		}
	}
	
	@Override
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
	
	@Override
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
