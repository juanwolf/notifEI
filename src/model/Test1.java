package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Test1 {
  public static void main(String[]argv) {    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("notifEI");    
    EntityManager em = emf.createEntityManager();    
    EntityTransaction transac = em.getTransaction();
    transac.begin();
    User user = new User();
    user.setId(6);
    user.setNom("Monmert");
    user.setPrenom("Thibaud");
    em.persist(user);
    transac.commit();
    
    em.close();    
    emf.close();  
  }    
}