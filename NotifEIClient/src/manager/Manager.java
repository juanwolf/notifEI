package manager;
 
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class Manager<E> {

	protected EntityManagerFactory emf = null;

    public Manager() { 
    	
    }
    
    public void init() {
	    emf = Persistence.createEntityManagerFactory("jpaExtends");
    }
    
    public void close() {
    	if (emf != null) {
    		emf.close();
    	}
    } 

	public abstract void create(E element);
	
	public abstract E retrieve(int id);
	
	public abstract List<E> retrieve(List<String> champs, List<String> valeurs);
	
	public abstract void update(E element);
	
	public abstract void delete(int id);

}
