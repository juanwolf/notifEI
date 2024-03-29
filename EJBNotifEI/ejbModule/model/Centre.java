package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value="Centre") 
public class Centre extends Utilisateur {
	private static final long serialVersionUID = 1L;
	
	@Column(name="Nom")
	private String nom;
	
	@Column(name="Telephone")
	private String telephone;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Adresse")
	private String adresse;
	
	@Column(name="Ville")
	private String ville;
	
	@Column(name="Code_Postal")
	private String codePostal;
	
	@OneToMany(mappedBy="centre", fetch=FetchType.EAGER)
	private List<Departement> departements;
	
	public Centre() {
		departements = new ArrayList<Departement>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public List<Departement> getDepartements() {
		return departements;
	}

	public void setDepartements(List<Departement> departements) {
		this.departements = departements;
	}
	
	public void addDepartement(Departement departement) {
        this.departements.add(departement);
        if (departement.getCentre() != this) {
            departement.setCentre(this);
        }
    }
	
	public String toString() {
		String stringDepartements = "";
		for (int i = 0; i < departements.size(); i++) {
			stringDepartements += departements.get(i).getNom() + "(" + departements.get(i).getNumero() + ") ; ";
		}
		return "Nom : " + nom 
			+ "<br/>Adresse : " + adresse 
			+ "<br/>Ville : " + ville
			+ "<br/>Code Postal : " + codePostal 
			+ "<br/>T�l�phone : " + telephone 
			+ "<br/>Email : " + email 
			+ "<br/>Departements : " + stringDepartements
			+ "<br/><hr/>"; 
	}

}
