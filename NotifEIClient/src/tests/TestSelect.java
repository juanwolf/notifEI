package tests;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import manager.*;
import model.*;

public class TestSelect {
	
	public static void main(String[] argv) {
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("notifEI");    
	    //EntityManager em = emf.createEntityManager(); 

	    /*Query query = em.createQuery("select u from User u where u.id = 2");
	    List<User> users = (List<User>) query.getResultList();
	    for (User user : users) {
	    	System.out.println("nom = "+ user.getNom() + " | prenom = " + user.getPrenom());
	    }*/
	    /*User user = em.find(User.class, 1);
	    if (user != null) {
	    	System.out.println("nom = "+ user.getNom() + " | prenom = " + user.getPrenom());
	    }*/
    
	    //em.close();
	    //emf.close();
		//DeclarationManager m = new DeclarationManager();
		//m.init();
		/*List<String> c = new ArrayList<String>();
		c.add("login");
		c.add("password");
		List<String> v = new ArrayList<String>();
		v.add("auvraque");
		v.add("2100625");
		List<Patient> p = m.retrieve(c, v);*/
		//System.out.println(p.get(0).getPrenom());
		//System.out.println(m.poids());
		//m.close();
		// capable de retrouver que le produit medical est un medicament ou un dispo ou un cosmetique
		/*ProduitMedicalManager pmm =  new ProduitMedicalManager();
		pmm.init();
		ProduitMedical pm = pmm.retrieve(1);
		pmm.close();
		System.out.println(pm.getClass());*/
	}
}