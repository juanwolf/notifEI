package model;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value="DispositifMedical")
public class DispositifMedical extends ProduitMedical {
	private static final long serialVersionUID = 1L;

	@Enumerated(EnumType.STRING)
	@Column
	private Risque risque;

	public DispositifMedical() {
	}

	public Risque getRisque() {
		return risque;
	}

	public void setRisque(Risque risque) {
		this.risque = risque;
	}
	
	public String toString() {
		String stringRisque = "";
		switch(risque) {
			case CLASSE_I :
				stringRisque = "Faible risque";
				break;
			case CLASSE_IIA :
				stringRisque = "Degré moyen";
				break;
			case CLASSE_IIB :
				stringRisque = "Niveau élevé";
				break;
			case CLASSE_III :
				stringRisque = "Critique";
				break;
			default :
				break;
		
		}
		return "Nom : " + getNom() 
			+ "<br/>Laboratoire : " + getLaboratoire().getNom()
			+ "<br/>Risque : " + stringRisque
			+ "<br/><hr/>"; 
	}

}