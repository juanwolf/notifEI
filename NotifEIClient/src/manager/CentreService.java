package manager;

import java.util.List;

import model.Centre;

public interface CentreService {
	
	public void init();
	public void close();
	public void create(Centre centre);
	public Centre retrieveById(int id);
	public Centre retrieveForConnexion(String login, String password);
	public Centre retrieveByLogin(String login);
	public List<Centre> retrieveByName(String nom);
	public List<Centre> retrieveAll();
	public void update(Centre centre);
	public void delete(int id);
	
}
