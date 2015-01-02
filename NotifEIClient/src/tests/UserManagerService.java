package tests;

import javax.ejb.Remote;
import javax.persistence.EntityManagerFactory;

@Remote
public interface UserManagerService {
	
	public void init();
	public void close();
	//public void create(User user);
	public User retrieve(int id);
	//public void update(User user);
	//public void delete(int id);
	public String print(); 

}
