package manager;
 
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.*;

@Stateless
public class EffetIndesirableManager implements EffetIndesirableService {

	private EntityManagerFactory emf = null;

    public EffetIndesirableManager() {
    	
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
	
	@Override
	public EffetIndesirable retrieveById(int id) {
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
	@Override
	public EffetIndesirable retrieveByName(String nom) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select e from EffetIndesirable e where e.nom='" + nom + "'";
			Query query = em.createQuery(requete);
			List<EffetIndesirable> list = (List<EffetIndesirable>) query.getResultList();
			if (list.size() == 1) {
				EffetIndesirable ei = list.get(0);
		    	return ei;
			} else {
				return null;
			}
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EffetIndesirable> retrieveAll() {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select e from EffetIndesirable e ";
			Query query = em.createQuery(requete);
			List<EffetIndesirable> effetsIndesirables = query.getResultList();
		    return effetsIndesirables;
		} finally {
			em.close();
		}
	}
	
	@Override
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
	
	@Override
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
