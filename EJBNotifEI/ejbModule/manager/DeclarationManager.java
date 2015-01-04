package manager;
 
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.*;

@Stateless
public class DeclarationManager implements DeclarationService {
	
	private EntityManagerFactory emf = null;

    public DeclarationManager() { 
    	
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
	public void create(Declaration declaration) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();    
			EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    em.persist(declaration);
		    transac.commit();   
		} finally {
			em.close(); 
		}
	}
	
    @Override
	public Declaration retrieveById(int id) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			Declaration declaration = em.find(Declaration.class, id);
		    return declaration;
		} finally {
			em.close();
		}
	}
	
    @SuppressWarnings("unchecked")
	@Override
	public Declaration retrieve(int idProduitMedical, int idEffetIndesirable, int idUtilisateur) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select d from Declaration d";
					/*+ "where d.produitMedical='" + idProduitMedical 
					+ "' and d.utilisateur='" + idUtilisateur 
					+ "' and d.effetIndesirable='" + idEffetIndesirable + "'";*/
			Query query = em.createQuery(requete);
			List<Declaration> list = (List<Declaration>) query.getResultList();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getProduitMedical().getId() == idProduitMedical 
					&& list.get(i).getEffetIndesirable().getId() == idEffetIndesirable
					&& list.get(i).getUtilisateur().getId() == idUtilisateur) {
					return list.get(i);
				}
			}
		    return null;
		} finally {
			em.close();
		}
	}
    
    @SuppressWarnings("unchecked")
    @Override
	public List<EffetIndesirable> retrieveAllDistinctEIs() {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select distinct d.effetIndesirable from Declaration d";
			Query query = em.createQuery(requete);
			List<EffetIndesirable> eis = (List<EffetIndesirable>) query.getResultList();
		    return eis;
		} finally {
			em.close();
		}
	}
    
    @SuppressWarnings("unchecked")
    @Override
	public List<ProduitMedical> retrieveAllDistinctPM() {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select distinct d.produitMedical from Declaration d";
			Query query = em.createQuery(requete);
			List<ProduitMedical> ProduitMedical = query.getResultList();
		    return ProduitMedical;
		} finally {
			em.close();
		}
	}
    
    @SuppressWarnings("unchecked")
	@Override
	public double retrievePoidsByPMAndEI(int ei, int pm) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select d from Declaration d";
			Query query = em.createQuery(requete);
			List<Declaration> list = (List<Declaration>) query.getResultList();
			double poids = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getProduitMedical().getId() == pm && list.get(i).getEffetIndesirable().getId() == ei) {
					poids += list.get(i).getPoids();
				}
			}
		    return poids;
		} finally {
			em.close();
		}
	}
    
    @SuppressWarnings("unchecked")
	@Override
	public List<Declaration> retrieveAll() {
		EntityManager em = null;
		try {
			em = emf.createEntityManager(); 
			String requete = "select d from Declaration d";
			Query query = em.createQuery(requete);
			List<Declaration> declarations = query.getResultList();
		    return declarations;
		} finally {
			em.close();
		}
	}
	
	@Override
	public void update(Declaration declaration) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
		    EntityTransaction transac = em.getTransaction();
		    transac.begin();
		    Declaration d = em.find(Declaration.class, declaration.getId());
		    if (d != null) {
		    	d.setPoids(declaration.getPoids());
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
		    Declaration declaration = em.find(Declaration.class, id);    
		    if (declaration != null) {
		      em.remove(declaration);      
		    }
		    transac.commit();
		} finally {		    
			em.close();
		} 
	}

}
