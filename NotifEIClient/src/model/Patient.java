package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue(value="Patient") 
public class Patient extends Utilisateur {
	private static final long serialVersionUID = 1L;
	
	@Column(name="Nom")
	private String nom;
	
	@Column(name="Prenom")
	private String prenom;
	
	@Column(name="Date_Naissance")
	private String dateNaissance;
	
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
	
	@ManyToMany
	@JoinTable(
		name="Patient_ProduitMedical",
		joinColumns={
			@JoinColumn(name="Id_Patient", referencedColumnName="Id")
		},
		inverseJoinColumns={
			@JoinColumn(name="Id_ProduitMedical", referencedColumnName="Id")
		})
	private List<ProduitMedical> produitsMedicaux;
	
	public Patient() {
		
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

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
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

	public List<ProduitMedical> getProduitsMedicaux() {
		return produitsMedicaux;
	}

	public void setProduitsMedicaux(List<ProduitMedical> produitsMedicaux) {
		this.produitsMedicaux = produitsMedicaux;
	}
	
	public String toString() {
		return "Nom : " + nom 
			+ "<br/>Pr�nom : " + prenom 
			+ "<br/>Date de naissance : " + dateNaissance
			+ "<br/>Adresse : " + adresse 
			+ "<br/>Ville : " + ville
			+ "<br/>Code Postal : " + codePostal 
			+ "<br/>T�l�phone : " + telephone 
			+ "<br/>Email : " + email 
			+ "<br/><hr/>"; 
	}

}
