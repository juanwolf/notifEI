package manager;

import java.util.List;

import model.Departement;
import model.Patient;

public interface PatientService {
	
	public void init();
	public void close();
	public void create(Patient patient);
	public Patient retrieveById(int id);
	public Patient retrieveForConnexion(String login, String password);
	public Patient retrieveByLogin(String login);
	public List<Patient> retrieveByNamesAndRegion(String nom, String prenom, List<Departement> departements);
	public List<Patient> retrieveAll();
	public void update(Patient patient);
	public void delete(int id);
	
}
