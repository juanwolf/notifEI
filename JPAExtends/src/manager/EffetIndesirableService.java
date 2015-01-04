package manager;

import java.util.List;

import javax.ejb.Remote;

import model.EffetIndesirable;

@Remote
public interface EffetIndesirableService {

	public void init();
	public void close();
	public void create(EffetIndesirable effetIndesirable);
	public EffetIndesirable retrieveById(int id);
	public List<EffetIndesirable> retrieveAll();
	public EffetIndesirable retrieveByName(String nom);
	public void update(EffetIndesirable effetIndesirable);
	public void delete(int id);
	
}
