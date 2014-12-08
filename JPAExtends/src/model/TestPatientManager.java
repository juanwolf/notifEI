package model;

import java.util.List;

public class TestPatientManager {
	
	public static void main(String[] argv) {
		PatientManager pm = new PatientManager();
		pm.init();
		try {
			Patient patient = new Patient();
		    patient.setId(1);
		    patient.setLogin("auvraque");
		    patient.setPassword("21006257");
		    patient.setRole(Role.PATIENT);
		    patient.setNom("Auvray");
		    patient.setPrenom("Quentin");
		    pm.create(patient);
		    System.out.println("Patient créé");
		} catch (Exception e) {
			System.out.println("Patient existe dèjà");
		}
		Patient p = pm.retrieve(1);
		if (p != null) {
			System.out.println("Nom : " + p.getNom() + " | Prenom : " + p.getPrenom());
		} else {
			System.out.println("La personne n'existe pas");
		}
		List<Patient> l = pm.retrieve("nom", "Auvray");
		System.out.println(l.size());
		for (int i = 0; i < l.size(); i++) {
			System.out.println("Nom : " + l.get(i).getNom() + " | Prenom : " + l.get(i).getPrenom());
		}
		
		
		pm.close();
	}

}
