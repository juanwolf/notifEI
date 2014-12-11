package model;

public class TestUserManager {
	public static void main(String[] argv) {
		
		// Création du manager
	    UserManager userManager = new UserManager();
	    
	    // Création de l'utilisateur + insertion dans la bdd
	    System.out.println("Création");
	    User user = new User();
	    user.setId(10);
	    user.setNom("Mwahaha");
	    user.setPrenom("Quentin");
	    userManager.create(user);
	    
	    // Recherche de l'utilisateur ayant l'id 10 + affihage du nom/prenom
	    System.out.println("Recherche");
	    user = userManager.retrieve(10);
	    if (user != null) {
	    	System.out.println("nom = "+ user.getNom() + " | prenom = " + user.getPrenom());
	    }
	    
	    // Modification de l'utilisateur
	    System.out.println("Modification");
	    user.setNom("Auvray");
	    userManager.update(user);
	    
	    // Véifaction que l'utilisateur a bien été modifié
	    System.out.println("Recherche");
	    user = userManager.retrieve(10);
	    if (user != null) {
	    	System.out.println("nom = "+ user.getNom() + " | prenom = " + user.getPrenom());
	    }
	    
	    // Suppression de l'utilisateur ayant l'id 10
	    System.out.println("Suppression");
	    userManager.delete(10);
	    
	    userManager.close();
	}
}
