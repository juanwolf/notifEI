package manager;

import java.util.List;

import javax.ejb.Remote;

import model.Laboratoire;

@Remote
public interface LaboratoireService {
	
	public void init();
	public void close();
	public void create(Laboratoire laboratoire);
	public Laboratoire retrieveById(int id);
	public Laboratoire retrieveForConnexion(String login, String password);
	public Laboratoire retrieveByLogin(String login);
	public List<Laboratoire> retrieveAll();
	public void update(Laboratoire laboratoire);
	public void delete(int id);

}
