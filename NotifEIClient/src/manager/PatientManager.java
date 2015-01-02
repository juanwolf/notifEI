package manager;
 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.*;

public class PatientManager implements PatientService {
	
	private EntityManagerFactory emf = null;

    public PatientManager() { 
    	
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
	public void create(Patient patient) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();    
			EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    em.persist(patient);
		    transac.commit();   
		} finally {
			em.close(); 
		}
	}
	
	@Override
	public Patient retrieveById(int id) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			Patient patient = em.find(Patient.class, id);
		    return patient;
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Patient retrieveForConnexion(String login, String password) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select p from Patient p "
					+ "where p.login='" + login 
					+ "' and p.password='" + password + "'";
			Query query = em.createQuery(requete);
			List<Patient> list = (List<Patient>) query.getResultList();
			if (list.size() == 1) {
				Patient patient = list.get(0);
		    	return patient;
			} else {
				return null;
			}
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Patient retrieveByLogin(String login) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select p from Patient p "
					+ "where p.login='" + login + "'";
			Query query = em.createQuery(requete);
			List<Patient> list = (List<Patient>) query.getResultList();
			if (list.size() == 1) {
				Patient patient = list.get(0);
		    	return patient;
			} else {
				return null;
			}
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> retrieveByNamesAndRegion(String nom, String prenom, List<Departement> departements) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select p from Patient p "
					+ "where p.nom like '" + nom 
					+ "%' and p.prenom like '" + prenom + "%'";
			if (departements != null && departements.size() > 0) {
				requete += " and (p.codePostal like '" + departements.get(0).getNumero() + "%'";
				for (int i = 1; i < departements.size(); i++) {
					requete += " or p.codePostal like '" + departements.get(i).getNumero() + "%'"; 
				}
				requete += ")";
			}
			Query query = em.createQuery(requete);
			List<Patient> patients = query.getResultList();
		    return patients;
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> retrieveAll() {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select p from Patient p ";
			Query query = em.createQuery(requete);
			List<Patient> patients = query.getResultList();
		    return patients;
		} finally {
			em.close();
		}
	}	
	
	@Override
	public void update(Patient patient) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
		    EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    Patient p = em.find(Patient.class, patient.getId());
		    if (p != null) {
		    	p.setNom(patient.getNom());
		    	p.setPrenom(patient.getPrenom());
		    	p.setDateNaissance(patient.getDateNaissance());
		    	p.setTelephone(patient.getTelephone());
		    	p.setEmail(patient.getEmail());
		    	p.setAdresse(patient.getAdresse());
		    	p.setVille(patient.getVille());
		    	p.setCodePostal(patient.getCodePostal());
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
		    Patient patient = em.find(Patient.class, id);    
		    if (patient != null) {
		      em.remove(patient);      
		    }
		    transac.commit();
		} finally {		    
			em.close();
		} 
	}

}
