package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ProduitMedical")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="Produit_Medical_Type")
public class ProduitMedical implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="Id")
	private Integer id;
	
	@Column(name="Nom")
	private String nom;
	
	@ManyToMany(mappedBy="produitsMedicaux")
	private List<Patient> patients;
	
	@ManyToOne
	@JoinColumn(name="laboratoire")
	private Laboratoire laboratoire;
	
	@ManyToMany(mappedBy="produitsMedicaux")
	private List<EffetIndesirable> effetsIndesirables;
	
	@OneToMany(mappedBy="produitMedical", fetch=FetchType.EAGER)
	private List<Declaration> declarations;
	
	public ProduitMedical() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public Laboratoire getLaboratoire() {
		return laboratoire;
	}

	public void setLaboratoire(Laboratoire laboratoire) {
		this.laboratoire = laboratoire;
	}

	public List<EffetIndesirable> getEffetsIndesirables() {
		return effetsIndesirables;
	}

	public void setEffetsIndesirables(List<EffetIndesirable> effetsIndesirables) {
		this.effetsIndesirables = effetsIndesirables;
	}

	public List<Declaration> getDeclarations() {
		return declarations;
	}

	public void setDeclarations(List<Declaration> declarations) {
		this.declarations = declarations;
	}
	
	public String toString() {
		return "Nom : " + nom 
			+ "<br/>Laboratoire : " + laboratoire.getNom()
			+ "<br/><hr/>"; 
	}
	
}
