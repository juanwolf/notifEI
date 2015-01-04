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
import javax.persistence.Table;

@Entity
@Table(name="ClasseChimique")
public class ClasseChimique implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="Id")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="mere")
	private ClasseChimique mere;
	
	@Column(name="nom")
	private String nom;
	
	@ManyToMany
	@JoinTable(
		name="ClasseChimique_SubstanceActive",
		joinColumns={
			@JoinColumn(name="Id_ClasseChimique", referencedColumnName="Id")
		},
		inverseJoinColumns={
			@JoinColumn(name="Id_SubstanceActive", referencedColumnName="Id")
		})
	private List<SubstanceActive> substancesActives;
	
	@ManyToMany
	@JoinTable(
		name="ClasseChimique_EffetIndesirable",
		joinColumns={
			@JoinColumn(name="Id_ClasseChimique", referencedColumnName="Id")
		},
		inverseJoinColumns={
			@JoinColumn(name="Id_Id_EffetIndesirable", referencedColumnName="Id")
		})
	private List<EffetIndesirable> effetsIndesirables;
	
	public ClasseChimique() {
		
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

	public ClasseChimique getMere() {
		return mere;
	}

	public void setMere(ClasseChimique mere) {
		this.mere = mere;
	}

	public List<SubstanceActive> getSubstancesActives() {
		return substancesActives;
	}

	public void setSubstancesActives(List<SubstanceActive> substancesActives) {
		this.substancesActives = substancesActives;
	}

	public List<EffetIndesirable> getEffetsIndesirables() {
		return effetsIndesirables;
	}

	public void setEffetsIndesirables(List<EffetIndesirable> effetsIndesirables) {
		this.effetsIndesirables = effetsIndesirables;
	}

}
