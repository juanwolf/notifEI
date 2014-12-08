package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestSelect {
	
	public static void main(String[] argv) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaExtends");    
	    EntityManager em = emf.createEntityManager(); 
	    /*EntityTransaction transac = em.getTransaction();
	    transac.begin();
	    Patient patient = new Patient();
	    patient.setId(1);
	    patient.setLogin("auvraque");
	    patient.setPassword("21006257");
	    patient.setRole(Role.PATIENT);
	    patient.setNom("Auvray");
	    patient.setPrenom("Quentin");
	    em.persist(patient);
	    transac.commit();
	    */
	    try {
	    	EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    Centre centre = new Centre();
		    centre.setId(5);
		    centre.setNom("Centre 5");
		    em.persist(centre);
		    transac.commit();
	    } catch(Exception e) {}
	    
	    Centre centre2 = em.find(Centre.class, 5);
	    System.out.println(centre2.getId());
	    System.out.println(centre2.getNom());
	    System.out.println(centre2.getDepartements());
	    System.out.println();
	    if (centre2 != null) {
	    	try {
			    EntityTransaction transac2 = em.getTransaction();
			    transac2.begin();
			    Departement dpt = new Departement();
			    dpt.setNumero("76");
			    dpt.setNom("Seine-Maritime");
			    dpt.setCentre(centre2);
			    em.persist(dpt);
			    transac2.commit();
	    	} catch (Exception e) {}
	    }
	    Centre centreBis = em.find(Centre.class, 5);
	    System.out.println(centreBis.getId());
	    System.out.println(centreBis.getNom());
	    System.out.println(centreBis.getDepartements());
	    
	    /*System.out.println("Insertion effectu�e");
	    Patient patient = em.find(Patient.class, 1);
	    if (patient != null) {
	    	System.out.println("nom = "+ patient.getNom() + " | prenom = " + patient.getPrenom());
	    	System.out.println("nom = "+ patient.getLogin() + " | prenom = " + patient.getPassword());
	    }*/
	    
	    em.close();
	    emf.close();
	}
}