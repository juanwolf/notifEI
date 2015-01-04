package servlet;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	    	InitialContext ic = new InitialContext();		
			String recherche = request.getParameter("recherche");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String produitMedical = request.getParameter("produits_medicaux");
			switch (recherche) {
				case "patients" : 
					if (request.getSession().getAttribute("utilisateur").getClass().equals(Centre.class)) {
						Centre centre = (Centre) request.getSession().getAttribute("utilisateur");
						// on récupère la liste des départements dont s'occupe le centre qui est connecté
						List<Departement> departements = centre.getDepartements();
						PatientService ps = (PatientService) ic.lookup(PatientService.class.getName());
						ps.init();
						List<Patient> patients = ps.retrieveByNamesAndRegion(nom, prenom, departements);
						ps.close();
						request.getSession().setAttribute("recherche", patients);
						request.getRequestDispatcher("recherche.jsp").forward(request, response);
					} else {
						response.sendRedirect("recherche.jsp");
					}
					break;
				case "produits_medicaux" : 
					ProduitMedicalService pms = (ProduitMedicalService) ic.lookup(ProduitMedicalService.class.getName());					
					pms.init();
					List<ProduitMedical> produitsMedicaux = pms.retrieveByName(nom);
					pms.close();
					request.getSession().setAttribute("recherche", produitsMedicaux);
					request.getRequestDispatcher("recherche.jsp").forward(request, response);
					break;
				case "effets_indesirables" :
					pms = (ProduitMedicalService) ic.lookup(ProduitMedicalService.class.getName());
					pms.init();
					ProduitMedical pm = (ProduitMedical) pms.retrieveByName(produitMedical).get(0);
					List<EffetIndesirable> effetsIndesirables = pm.getEffetsIndesirables();
					pms.close();
					request.getSession().setAttribute("recherche", effetsIndesirables);
					System.out.println(effetsIndesirables.size());
					//response.sendRedirect("recherche.jsp");
					request.getRequestDispatcher("recherche.jsp").forward(request, response);
					System.out.println("6");
					break;
				case "medecins" :
					if (request.getSession().getAttribute("utilisateur").getClass().equals(Centre.class)) {
						Centre centre = (Centre) request.getSession().getAttribute("utilisateur");
						// on récupère la liste des départements dont s'occupe le centre qui est connecté
						List<Departement> departements = centre.getDepartements();
						MedecinService ms = (MedecinService) ic.lookup(MedecinService.class.getName());
						ms.init();
						List<Medecin> medecins = ms.retrieveByNamesAndRegion(nom, prenom, departements);
						ms.close();
						request.getSession().setAttribute("recherche", medecins);
						request.getRequestDispatcher("recherche.jsp").forward(request, response);
					} else {
						response.sendRedirect("recherche.jsp");
					}
					break;
				case "centres" : 
					if (request.getSession().getAttribute("utilisateur").getClass().equals(Centre.class)) {
						Centre centre = (Centre) request.getSession().getAttribute("utilisateur");
						if (centre.getNom().equals("CNPV")) {
							CentreService cs = (CentreService) ic.lookup(CentreService.class.getName());
							cs.init();
							List<Centre> centres = cs.retrieveByName(nom);
							cs.close();
							request.getSession().setAttribute("recherche", centres);
							request.getRequestDispatcher("recherche.jsp").forward(request, response);
						} else {
							response.sendRedirect("recherche.jsp");
						}
					} else {
						response.sendRedirect("recherche.jsp");
					}
					break;
				default : 
					response.sendRedirect("recherche.jsp");
					break;
			}
		} catch (NamingException e) {
			System.out.println("erreur");
		}
	}
}
