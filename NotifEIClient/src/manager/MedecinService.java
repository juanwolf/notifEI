package manager;

import java.util.List;

import model.Departement;
import model.Medecin;

public interface MedecinService {
	
	public void init();
	public void close();
	public void create(Medecin medecin);
	public Medecin retrieveById(int id);
	public Medecin retrieveForConnexion(String login, String password);
	public Medecin retrieveByLogin(String login);
	public List<Medecin> retrieveByNamesAndRegion(String nom, String prenom, List<Departement> departements);
	public List<Medecin> retrieveAll();
	public void update(Medecin medecin);
	public void delete(int id);

}
