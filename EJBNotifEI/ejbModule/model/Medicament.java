package model;

import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value="Medicament")
public class Medicament extends ProduitMedical {
	private static final long serialVersionUID = 1L;

	@Column(name="Indications")
	private String indications;
	
	@Column(name="Contre_Indications")
	private String contreIndications;
	
	@ManyToMany
	@JoinTable(
		name="Medicament_SubstanceActive",
		joinColumns={
			@JoinColumn(name="Id_Medicament", referencedColumnName="Id")
		},
		inverseJoinColumns={
			@JoinColumn(name="Id_SubstanceActive", referencedColumnName="Id")
		})
	private List<SubstanceActive> substancesActives;

	public Medicament() {
	}

	public String getIndications() {
		return indications;
	}

	public void setIndications(String indications) {
		this.indications = indications;
	}

	public String getContreIndications() {
		return contreIndications;
	}

	public void setContreIndications(String contreIndications) {
		this.contreIndications = contreIndications;
	}
	
	public String toString() {
		return "Nom : " + getNom() 
			+ "<br/>Laboratoire : " + getLaboratoire().getNom()
			+ "<br/>Indications : " + indications
			+ "<br/>Contre-indications : " + contreIndications
			+ "<br/><hr/>"; 
	}

}