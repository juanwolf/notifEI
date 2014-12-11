package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestUpdate {
  public static void main(String[] argv) {
    EntityManagerFactory emf = Persistence
        .createEntityManagerFactory("notifEI");
    EntityManager em = emf.createEntityManager();

    EntityTransaction transac = em.getTransaction();
    transac.begin();

    //Query query = em.createQuery("select u from User u where u.id = 2");
    //User user = (User) query.getSingleResult();
    User user = em.find(User.class, 2);
    if (user == null) {
      System.out.println("Personne non trouvée");
    } else {
      System.out.println("Personne.prenom=" + user.getPrenom());

      user.setPrenom("prenom2e");
      em.flush();
      
      user = em.find(User.class, 2);
      System.out.println("Personne.prenom=" + user.getPrenom());
    }
    
    transac.commit();
    
    em.close();
    emf.close();
  }
}