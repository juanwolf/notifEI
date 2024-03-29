package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Departement")
public class Departement implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="Numero")
	private String numero;
	
	@Column(name="Nom")
	private String nom;
	
	@ManyToOne
	@JoinColumn(name="Centre")
	private Centre centre;
	
	public Departement() {
		
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Centre getCentre() {
		return centre;
	}

	public void setCentre(Centre centre) {
        this.centre = centre;
        if (!centre.getDepartements().contains(this)) {
            centre.getDepartements().add(this);
        }
    }

}
