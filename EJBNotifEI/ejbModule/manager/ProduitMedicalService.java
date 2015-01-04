package manager;

import java.util.List;

import javax.ejb.Remote;

import model.ProduitMedical;

@Remote
public interface ProduitMedicalService {

	public void init();
	public void close();
	public void create(ProduitMedical produitMedical);
	public ProduitMedical retrieveById(int id);
	public List<ProduitMedical> retrieveByName(String nom);
	public List<ProduitMedical> retrieveAll();
	public void update(ProduitMedical produitMedical);
	public void delete(int id);
	
}
