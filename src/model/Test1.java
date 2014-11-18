package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Test1 {
  public static void main(String[]argv) {    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MaBaseDeTestPU");    
    EntityManager em = emf.createEntityManager();    
    EntityTransaction transac = em.getTransaction();
    transac.begin();
    User user = new User();
    user.setId(3);
    user.setNom("lmvljv");
    user.setPrenom("digf");
    em.persist(user);
    transac.commit();
    
    em.close();    
    emf.close();  
  }    
}