package servlet;

import java.io.IOException;
import java.util.List;

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
					PatientManager pm = new PatientManager();
					pm.init();
					List<Patient> patients = pm.retrieveByNamesAndRegion(nom, prenom, departements);
					pm.close();
					request.getSession().setAttribute("recherche", patients);
					request.getRequestDispatcher("recherche.jsp").forward(request, response);
				} else {
					response.sendRedirect("recherche.jsp");
				}
				break;
			case "produits_medicaux" : 
				ProduitMedicalManager pmm = new ProduitMedicalManager();
				pmm.init();
				List<ProduitMedical> produitsMedicaux = pmm.retrieveByName(nom);
				pmm.close();
				request.getSession().setAttribute("recherche", produitsMedicaux);
				request.getRequestDispatcher("recherche.jsp").forward(request, response);
				break;
			case "effets_indesirables" :
				pmm = new ProduitMedicalManager();
				pmm.init();
				ProduitMedical pm = (ProduitMedical) pmm.retrieveByName(produitMedical).get(0);
				List<EffetIndesirable> effetsIndesirables = pm.getEffetsIndesirables();
				pmm.close();
				request.getSession().setAttribute("recherche", effetsIndesirables);
				request.getRequestDispatcher("recherche.jsp").forward(request, response);
				break;
			case "medecins" :
				if (request.getSession().getAttribute("utilisateur").getClass().equals(Centre.class)) {
					Centre centre = (Centre) request.getSession().getAttribute("utilisateur");
					// on récupère la liste des départements dont s'occupe le centre qui est connecté
					List<Departement> departements = centre.getDepartements();
					MedecinManager mm = new MedecinManager();
					mm.init();
					List<Medecin> medecins = mm.retrieveByNamesAndRegion(nom, prenom, departements);
					mm.close();
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
						CentreManager cm = new CentreManager();
						cm.init();
						List<Centre> centres = cm.retrieveByName(nom);
						cm.close();
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
	}


}
