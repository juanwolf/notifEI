package manager;

import java.util.List;

import javax.ejb.Remote;

import model.*;

@Remote
public interface DeclarationService {
		
	public void init();
	public void close();
	public void create(Declaration declaration);
	public Declaration retrieveById(int id);
	public List<EffetIndesirable> retrieveAllDistinctEIs();
	public List<ProduitMedical> retrieveAllDistinctPM();
	public double retrievePoidsByPMAndEI(int ei, int pm);
	public Declaration retrieve(int idProduitMedical, int idEffetIndesirable, int idUtilisateur);
	public List<Declaration> retrieveAll();
	public void update(Declaration declaration);
	public void delete(int id);

}
