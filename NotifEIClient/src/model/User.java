package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "User" database table.
 * 
 */
@Entity
@Table(name="\"User\"")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"Id\"")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column
	private Role role;

	@Column(name="\"Nom\"")
	private String nom;

	@Column(name="\"Prenom\"")
	private String prenom;

	//bi-directional many-to-many association to Medicament
	@ManyToMany
	@JoinTable(
		name="\"User_Medicament\""
		, joinColumns={
				@JoinColumn(name="\"users_Id\"", referencedColumnName="\"Id\"")
			}
		, inverseJoinColumns={
				@JoinColumn(name="\"medicaments_Id\"", referencedColumnName="\"Id\"")
			}
		)
	private List<Medicament> medicaments;

	public User() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public List<Medicament> getMedicaments() {
		return this.medicaments;
	}

	public void setMedicaments(List<Medicament> medicaments) {
		this.medicaments = medicaments;
	}

}