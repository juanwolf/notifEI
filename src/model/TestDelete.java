package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestDelete {
  public static void main(String[] argv) {
    EntityManagerFactory emf = Persistence
        .createEntityManagerFactory("notifEI");
    EntityManager em = emf.createEntityManager();

    EntityTransaction transac = em.getTransaction();
    transac.begin();

    User user = em.find(User.class, 5);    
    if (user == null) {
      System.out.println("Personne non trouvée");
    } else {
      em.remove(user);      
    }
    
    transac.commit();
    
    em.close();
  }
}