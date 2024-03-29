package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="EffetIndesirable")
public class EffetIndesirable implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="Id")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="pere")
	private EffetIndesirable pere;
	
	@Column(name="nom")
	private String nom;
	
	@ManyToMany
	@JoinTable(
		name="EffetIndesirable_ProduitMedical",
		joinColumns={
			@JoinColumn(name="Id_EffetIndesirable", referencedColumnName="Id")
		},
		inverseJoinColumns={
			@JoinColumn(name="Id_ProduitMedical", referencedColumnName="Id")
		})
	private List<ProduitMedical> produitsMedicaux;
	
	@ManyToMany(mappedBy="effetsIndesirables")
	private List<ClassePharmacologique> classesPharmacologiques;
	
	@ManyToMany(mappedBy="effetsIndesirables")
	private List<ClasseChimique> classesChimiques;
	
	@OneToMany(mappedBy="effetIndesirable", fetch=FetchType.EAGER)
	private List<Declaration> declarations;
	
	public EffetIndesirable() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EffetIndesirable getPere() {
		return pere;
	}

	public void setPere(EffetIndesirable pere) {
		this.pere = pere;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<ProduitMedical> getProduitsMedicaux() {
		return produitsMedicaux;
	}

	public void setProduitsMedicaux(List<ProduitMedical> produitsMedicaux) {
		this.produitsMedicaux = produitsMedicaux;
	}

	public List<ClassePharmacologique> getClassesPharmacologiques() {
		return classesPharmacologiques;
	}

	public void setClassesPharmacologiques(
			List<ClassePharmacologique> classesPharmacologiques) {
		this.classesPharmacologiques = classesPharmacologiques;
	}

	public List<ClasseChimique> getClassesChimiques() {
		return classesChimiques;
	}

	public void setClassesChimiques(List<ClasseChimique> classesChimiques) {
		this.classesChimiques = classesChimiques;
	}

	public List<Declaration> getDeclarations() {
		return declarations;
	}

	public void setDeclarations(List<Declaration> declarations) {
		this.declarations = declarations;
	}
	
	public String toString() {
		return "Nom : " + nom 
			+ "<br/><hr/>"; 
	}

}
