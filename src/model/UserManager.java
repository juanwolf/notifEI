package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UserManager {
	private EntityManagerFactory emf = null;
    
    public UserManager() {
    	init();
    }
    
    public void init() {
	    emf = Persistence.createEntityManagerFactory("notifEI");
    }
    
    public void close() {
    	if (emf != null) {
    		emf.close();
    	}
    }

	public void create(User user) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();    
			EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    em.persist(user);
		    transac.commit();   
		} finally {
			em.close(); 
		}
	}
	
	public User retrieve(int id) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			User user = em.find(User.class, id);
		    return user;
		} finally {
			em.close();
		}
	}
	
	public void update(User user) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
		    EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    User u = em.find(User.class, user.getId());
		    if (u != null) {
		    	u.setPrenom(user.getPrenom());
		    	u.setNom(user.getNom());
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
		    User user = em.find(User.class, id);    
		    if (user != null) {
		      em.remove(user);      
		    }
		    transac.commit();
		} finally {		    
			em.close();
		}
	}
}
