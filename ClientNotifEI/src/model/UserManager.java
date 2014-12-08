package model;
 
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/** 
 * Session Bean implementation class UserManger
 */
@Stateless
public class UserManager implements UserManagerService {

	private EntityManagerFactory emf = null;
	
    /**
     * Default constructor. 
     */
    public UserManager() { 
    	
    }
    
    @Override
    public void init() {
	    emf = Persistence.createEntityManagerFactory("notifEI");
    }
    
    @Override
    public void close() {
    	if (emf != null) {
    		emf.close();
    	}
    } 

    /*@Override
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
	} */
	
	@Override
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
	
	/*@Override
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
	
	@Override
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
	}*/
    
    public String print() {
    	return "ça marche";
    }

}
