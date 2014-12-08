package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="SubstanceActive")
public class SubstanceActive implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Id")
	private Integer id;
	
	@Column(name="Nom")
	private String nom;
	
	@ManyToMany(mappedBy="substancesActives")
	private List<ClasseChimique> classesChimiques;
	
	@ManyToMany(mappedBy="substancesActives")
	private List<ClassePharmacologique> classesPharmacologiques;
	
	@ManyToMany(mappedBy="substancesActives")
	private List<Medicament> medicaments;
	
	public SubstanceActive() {
		
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

	public List<ClasseChimique> getClassesChimiques() {
		return classesChimiques;
	}

	public void setClassesChimiques(List<ClasseChimique> classesChimiques) {
		this.classesChimiques = classesChimiques;
	}

	public List<ClassePharmacologique> getClassesPharmacologiques() {
		return classesPharmacologiques;
	}

	public void setClassesPharmacologiques(
			List<ClassePharmacologique> classesPharmacologiques) {
		this.classesPharmacologiques = classesPharmacologiques;
	}

	public List<Medicament> getMedicaments() {
		return medicaments;
	}

	public void setMedicaments(List<Medicament> medicaments) {
		this.medicaments = medicaments;
	}
	
}
