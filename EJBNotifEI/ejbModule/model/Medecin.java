package model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="Medecin") 
public class Medecin extends Utilisateur {
	private static final long serialVersionUID = 1L;
	
	@Column(name="Nom")
	private String nom;
	
	@Column(name="Prenom")
	private String prenom;
	
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
	
	@Column(name="Qualification")
	private String qualification;
	
	@Column(name="Lieu_travail")
	private String lieuTravail;
	
	public Medecin() {
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getLieuTravail() {
		return lieuTravail;
	}

	public void setLieuTravail(String lieuTravail) {
		this.lieuTravail = lieuTravail;
	}
	
	public String toString() {
		return "Nom : " + nom 
			+ "<br/>Prénom : " + prenom 
			+ "<br/>Adresse : " + adresse 
			+ "<br/>Ville : " + ville
			+ "<br/>Code Postal : " + codePostal 
			+ "<br/>Téléphone : " + telephone 
			+ "<br/>Email : " + email 
			+ "<br/>Qualification : " + qualification
			+ "<br/>Lieu de travail : " + lieuTravail
			+ "<br/><hr/>"; 
	}

}
