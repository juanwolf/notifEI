package model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestSelect {
	
	public static void main(String[] argv) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("notifEI");    
	    EntityManager em = emf.createEntityManager(); 

	    /*Query query = em.createQuery("select u from User u where u.id = 2");
	    List<User> users = (List<User>) query.getResultList();
	    for (User user : users) {
	    	System.out.println("nom = "+ user.getNom() + " | prenom = " + user.getPrenom());
	    }*/
	    User user = em.find(User.class, 2);
	    if (user != null) {
	    	System.out.println("nom = "+ user.getNom() + " | prenom = " + user.getPrenom());
	    }
    
	    em.close();
	    emf.close();
	}
}