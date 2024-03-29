package model;

import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value="ProduitCosmetique")
public class ProduitCosmetique extends ProduitMedical {
	private static final long serialVersionUID = 1L;

	@Column(name="Indications")
	private String indications;
	
	@Column(name="Contre_Indications")
	private String contreIndications;
	
	@ManyToMany(mappedBy="produitsCosmetiques")
	private List<Ingredient> ingredients;

	public ProduitCosmetique() {
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

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public String toString() {
		return "Nom : " + getNom() 
			+ "<br/>Laboratoire : " + getLaboratoire().getNom()
			+ "<br/>Indications : " + indications
			+ "<br/>Contre-indications : " + contreIndications
			+ "<br/><hr/>"; 
	}

}