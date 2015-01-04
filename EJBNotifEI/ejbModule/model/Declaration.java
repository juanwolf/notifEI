package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Declaration
 *
 */
@Entity
@Table(name="Declaration")
public class Declaration implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="Id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="EffetIndesirable")
	private EffetIndesirable effetIndesirable;
	
	@ManyToOne
	@JoinColumn(name="ProduitMedical")
	private ProduitMedical produitMedical;
	
	@ManyToOne
	@JoinColumn(name="Utilisateur")
	private Utilisateur utilisateur;
	
	@Column(name="Poids")
	private Double poids;

	public Declaration() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EffetIndesirable getEffetIndesirable() {
		return effetIndesirable;
	}

	public void setEffetIndesirable(EffetIndesirable effetIndesirable) {
		this.effetIndesirable = effetIndesirable;
	}

	public ProduitMedical getProduitMedical() {
		return produitMedical;
	}

	public void setProduitMedical(ProduitMedical produitMedical) {
		this.produitMedical = produitMedical;
	}

	public Double getPoids() {
		return poids;
	}

	public void setPoids(Double poids) {
		this.poids = poids;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
   
}
