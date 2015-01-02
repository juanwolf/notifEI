package manager;
 
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.*;

public class MedecinManager implements MedecinService {

	private EntityManagerFactory emf = null;

    public MedecinManager() { 
    	
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
	public void create(Medecin medecin) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();    
			EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    em.persist(medecin);
		    transac.commit();   
		} finally {
			em.close(); 
		}
	}
	
	@Override
	public Medecin retrieveById(int id) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			Medecin medecin = em.find(Medecin.class, id);
		    return medecin;
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Medecin retrieveForConnexion(String login, String password) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select m from Medecin m "
					+ "where m.login='" + login 
					+ "' and m.password='" + password + "'";
			Query query = em.createQuery(requete);
			List<Medecin> list = (List<Medecin>) query.getResultList();
			if (list.size() == 1) {
				Medecin medecin = list.get(0);
		    	return medecin;
			} else {
				return null;
			}
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Medecin retrieveByLogin(String login) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select m from Medecin m "
					+ "where m.login='" + login + "'";
			Query query = em.createQuery(requete);
			List<Medecin> list = (List<Medecin>) query.getResultList();
			if (list.size() == 1) {
				Medecin medecin = list.get(0);
		    	return medecin;
			} else {
				return null;
			}
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Medecin> retrieveByNamesAndRegion(String nom, String prenom, List<Departement> departements) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select m from Medecin m "
					+ "where m.nom like '" + nom 
					+ "%' and m.prenom like '" + prenom + "%'";
			if (departements != null && departements.size() > 0) {
				requete += " and (m.codePostal like '" + departements.get(0).getNumero() + "%'";
				for (int i = 1; i < departements.size(); i++) {
					requete += " or m.codePostal like '" + departements.get(i).getNumero() + "%'"; 
				}
				requete += ")";
			}
			Query query = em.createQuery(requete);
			List<Medecin> medecins = query.getResultList();
		    return medecins;
		} finally {
			em.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Medecin> retrieveAll() {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select m from Medecin m ";
			Query query = em.createQuery(requete);
			List<Medecin> medecins = query.getResultList();
		    return medecins;
		} finally {
			em.close();
		}
	}
	
	@Override
	public void update(Medecin medecin) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
		    EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    Medecin m = em.find(Medecin.class, medecin.getId());
		    if (m != null) {
		    	m.setNom(medecin.getNom());
		    	m.setPrenom(medecin.getPrenom());
		    	m.setTelephone(medecin.getTelephone());
		    	m.setEmail(medecin.getEmail());
		    	m.setAdresse(medecin.getAdresse());
		    	m.setVille(medecin.getVille());
		    	m.setCodePostal(medecin.getCodePostal());
		    	m.setQualification(medecin.getQualification());
		    	m.setLieuTravail(medecin.getLieuTravail());
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
		    Medecin medecin = em.find(Medecin.class, id);    
		    if (medecin != null) {
		      em.remove(medecin);      
		    }
		    transac.commit();
		} finally {		    
			em.close();
		} 
	}

}
