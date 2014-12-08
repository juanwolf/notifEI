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

}