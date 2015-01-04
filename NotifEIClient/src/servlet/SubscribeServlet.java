package servlet;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.*;
import model.*;

/**
 * Servlet implementation class SubscribeServlet
 */
@WebServlet("/SubscribeServlet")
public class SubscribeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubscribeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	    	InitialContext ic = new InitialContext();
			
			// on récupère tous les éléments
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String role = request.getParameter("role");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String dateNaissance = request.getParameter("dateNaissance");
			String telephone = request.getParameter("telephone");
			String email = request.getParameter("email");
			String adresse = request.getParameter("adresse");
			String ville = request.getParameter("ville");
			String codePostal = request.getParameter("codePostal");
			String qualifications = request.getParameter("qualifications");
			String lieuTravail = request.getParameter("lieuTravail");
			
			// on vérifie si le login et le mot de passe ne sont pas nuls
			if (!login.isEmpty() && !password.isEmpty() && !nom.isEmpty() && !telephone.isEmpty()
					&& !email.isEmpty() && !adresse.isEmpty() &&!ville.isEmpty() && !codePostal.isEmpty()) {
				PatientService ps = (PatientService) ic.lookup(PatientService.class.getName());
				MedecinService ms = (MedecinService) ic.lookup(MedecinService.class.getName());
				LaboratoireService ls = (LaboratoireService) ic.lookup(LaboratoireService.class.getName());
				CentreService cs = (CentreService) ic.lookup(CentreService.class.getName());
				ps.init();
				ms.init();
				ls.init();
				cs.init();
				if (ps.retrieveByLogin(login) == null &&
					ms.retrieveByLogin(login) == null &&
					ls.retrieveByLogin(login) == null &&
					cs.retrieveByLogin(login) == null) {
					int id = ps.retrieveAll().size() + ms.retrieveAll().size() 
						+ ls.retrieveAll().size() + cs.retrieveAll().size() + 1; 
					switch(role) {
					case "patient" : 
						if (!prenom.isEmpty() && !dateNaissance.isEmpty()) {
							// il faut s'assurer que le login n'existe pas déjà
							Patient p = new Patient();
							p.setId(id);
							p.setLogin(login);
							p.setPassword(password);
							p.setRole(Role.PATIENT);
							p.setNom(nom);
							p.setPrenom(prenom);
							p.setDateNaissance(dateNaissance);
							p.setAdresse(adresse);
							p.setVille(ville);
							p.setCodePostal(codePostal);
							p.setEmail(email);
							p.setTelephone(telephone);
							ps.create(p);
							if (p != null) {
								request.getSession().setAttribute("utilisateur", p);
								request.getRequestDispatcher("accueil.jsp").forward(request, response);
							}
						} else {
							request.getSession().setAttribute("erreur", "Certains champs n'ont pas été remplis");
							request.getRequestDispatcher("inscription.jsp").forward(request, response);
						}
						break;
					case "association-patients" :
						// il faut s'assurer que le login n'existe pas déjà
						Patient p = new Patient();
						p.setId(id);
						p.setLogin(login);
						p.setPassword(password);
						p.setRole(Role.ASSOCIATION_PATIENTS);
						p.setNom(nom);
						p.setAdresse(adresse);
						p.setVille(ville);
						p.setCodePostal(codePostal);
						p.setEmail(email);
						p.setTelephone(telephone);
						ps.create(p);
						if (p != null) {
							request.getSession().setAttribute("utilisateur", p);
							request.getRequestDispatcher("accueil.jsp").forward(request, response);
						}
						break;
					case "medecin-hospitalier" : 
						if (!prenom.isEmpty() && !qualifications.isEmpty() && !lieuTravail.isEmpty()) {
							// il faut s'assurer que le login n'existe pas déjà
							Medecin m = new Medecin();
							m.setId(id);
							m.setLogin(login);
							m.setPassword(password);
							m.setRole(Role.MEDECIN_HOSPITALIER);
							m.setNom(nom);
							m.setPrenom(prenom);
							m.setAdresse(adresse);
							m.setVille(ville);
							m.setCodePostal(codePostal);
							m.setEmail(email);
							m.setTelephone(telephone);
							m.setQualification(qualifications);
							m.setLieuTravail(lieuTravail);
							ms.create(m);
							if (m != null) {
								request.getSession().setAttribute("utilisateur", m);
								request.getRequestDispatcher("accueil.jsp").forward(request, response);
							}
						} else {
							request.getSession().setAttribute("erreur", "Certains champs n'ont pas été remplis");
							request.getRequestDispatcher("inscription.jsp").forward(request, response);
						}
						break;
					case "medecin-ville" :
						if (!prenom.isEmpty() && !qualifications.isEmpty() && !lieuTravail.isEmpty()) {
							// il faut s'assurer que le login n'existe pas déjà
							Medecin m = new Medecin();
							m.setId(id);
							m.setLogin(login);
							m.setPassword(password);
							m.setRole(Role.MEDECIN_VILLE);
							m.setNom(nom);
							m.setPrenom(prenom);
							m.setAdresse(adresse);
							m.setVille(ville);
							m.setCodePostal(codePostal);
							m.setEmail(email);
							m.setTelephone(telephone);
							m.setQualification(qualifications);
							m.setLieuTravail(lieuTravail);
							ms.create(m);
							if (m != null) {
								request.getSession().setAttribute("utilisateur", m);
								request.getRequestDispatcher("accueil.jsp").forward(request, response);
							}
						} else {
							request.getSession().setAttribute("erreur", "Certains champs n'ont pas été remplis");
							request.getRequestDispatcher("inscription.jsp").forward(request, response);
						}
						break;
					case "laboratoire-pharmaceutique" : 
						// il faut s'assurer que le login n'existe pas déjà
						Laboratoire l = new Laboratoire();
						l.setId(id);
						l.setLogin(login);
						l.setPassword(password);
						l.setRole(Role.LABORATOIRE_PHARAMACEUTIQUE);
						l.setNom(nom);
						l.setAdresse(adresse);
						l.setVille(ville);
						l.setCodePostal(codePostal);
						l.setEmail(email);
						l.setTelephone(telephone);
						ls.create(l);
						if (l != null) {
							request.getSession().setAttribute("utilisateur", l);
							request.getRequestDispatcher("accueil.jsp").forward(request, response);
						}
						break;
					case "laboratoire-cosmetique" :
						// il faut s'assurer que le login n'existe pas déjà
						l = new Laboratoire();
						l.setId(id);
						l.setLogin(login);
						l.setPassword(password);
						l.setRole(Role.ASSOCIATION_PATIENTS);
						l.setNom(nom);
						l.setAdresse(adresse);
						l.setVille(ville);
						l.setCodePostal(codePostal);
						l.setEmail(email);
						l.setTelephone(telephone);
						ls.create(l);
						if (l != null) {
							request.getSession().setAttribute("utilisateur", l);
							request.getRequestDispatcher("accueil.jsp").forward(request, response);
						}
						break;
					default : 
						break;
					}
				} else {
					request.getSession().setAttribute("erreur", "Ce login existe déjà");
					request.getRequestDispatcher("inscription.jsp").forward(request, response);
				}
				ps.close();
				ms.close();
				ls.close();
				cs.close();
			} else {
				request.getSession().setAttribute("erreur", "Certains champs n'ont pas été remplis");
				request.getRequestDispatcher("inscription.jsp").forward(request, response);
			}
		} catch (NamingException e) {
			System.out.println("erreur");
		}
	}
}
