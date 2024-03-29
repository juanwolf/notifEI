package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue(value="Laboratoire") 
public class Laboratoire extends Utilisateur {
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
	
	@OneToMany(mappedBy="laboratoire", fetch=FetchType.EAGER)
	private List<ProduitMedical> produitsMedicaux;
	
	public Laboratoire() {
		
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

	public List<ProduitMedical> getProduitsMedicaux() {
		return produitsMedicaux;
	}

	public void setProduitsMedicaux(List<ProduitMedical> produitsMedicaux) {
		this.produitsMedicaux = produitsMedicaux;
	}

}
