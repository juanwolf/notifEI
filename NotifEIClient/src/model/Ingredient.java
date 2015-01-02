package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Ingredient")
public class Ingredient implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="Id")
	private Integer id;
	
	@Column(name="Nom")
	private String nom;
	
	@ManyToMany
	@JoinTable(
		name="Ingredient_ProduitCosmetique",
		joinColumns={
			@JoinColumn(name="Id_Ingredient", referencedColumnName="Id")
		},
		inverseJoinColumns={
			@JoinColumn(name="Id_ProduitCosmetique", referencedColumnName="Id")
		})
	private List<ProduitCosmetique> produitsCosmetiques;
	
	public Ingredient() {
		
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

	public List<ProduitCosmetique> getProduitsCosmetiques() {
		return produitsCosmetiques;
	}

	public void setProduitsCosmetiques(List<ProduitCosmetique> produitsCosmetiques) {
		this.produitsCosmetiques = produitsCosmetiques;
	}

}
