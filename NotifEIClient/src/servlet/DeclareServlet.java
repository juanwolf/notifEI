package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import manager.*;
import model.*;

/**
 * Servlet implementation class DeclareServlet
 */
@WebServlet("/DeclareServlet")
public class DeclareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeclareServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String produitMedical = request.getParameter("produit_medical");
		String effetIndesirable = request.getParameter("effet_indesirable");
		String checkbox = request.getParameter("checkbox");
		if (!produitMedical.isEmpty() && !effetIndesirable.isEmpty()) {
			// On récupère d'abord les objets associés au nom du produit et au nom de l'effet indésirable
			ProduitMedicalManager pmm = new ProduitMedicalManager();
			pmm.init();
			ProduitMedical p = pmm.retrieveByName(produitMedical).get(0); 
			pmm.close();
			EffetIndesirableManager eim = new EffetIndesirableManager();
			eim.init();
			EffetIndesirable ei = eim.retrieveByName(effetIndesirable);
			eim.close();
			
			// On vérifie si cet utilisateur a déjà associé cet effet indésirable à ce médicament
			// ou si l'effet indésirable est déjà connu
			DeclarationManager dm = new DeclarationManager();
			dm.init();
			Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("utilisateur");
			Declaration d = dm.retrieve(p.getId(), ei.getId(), utilisateur.getId());
			boolean contains = false;
			for (int i = 0; i < ei.getProduitsMedicaux().size() && !contains; i++) {
				if (ei.getProduitsMedicaux().get(i).getNom().equals(p.getNom())) {
					contains = true;
				}
			}
			if (d != null || contains) {
				if (d != null) {
					// alors cette personne a déjà déclaré cet effet indésirable pour ce médicament
					request.getSession().setAttribute("erreur", "Vous avez déjà notifié cet effet indésirable");
					request.getRequestDispatcher("declaration.jsp").forward(request, response);
				} else {
					// alors l'effet est déjà connu
					request.getSession().setAttribute("erreur", "Cet effet indésirable est déjà connu pour ce médicament");
					request.getRequestDispatcher("declaration.jsp").forward(request, response);
				}
			} else {
				Declaration declaration = new Declaration();
				int id = dm.retrieveAll().size() + 1;
				declaration.setId(id);
				declaration.setEffetIndesirable(ei);
				declaration.setProduitMedical(p);
				declaration.setUtilisateur(utilisateur);
				if (!p.getClass().equals(ProduitCosmetique.class)) {
					switch (utilisateur.getRole()) {
						case PATIENT :
							// Utilisateur quelconque
							if (checkbox.isEmpty()) {
								declaration.setPoids(0.5);
							} else {
								declaration.setPoids(0.75);
							}
							break;
						case ASSOCIATION_PATIENTS :
							declaration.setPoids(1.);
							break;
						case MEDECIN_VILLE :
							declaration.setPoids(1.25);
							break;
						case MEDECIN_HOSPITALIER :
							declaration.setPoids(1.5);
							break;
						case CRPV :
							declaration.setPoids(2.);
							break;
						case CNPV :
							declaration.setPoids(3.);
							break;
						case LABORATOIRE_PHARAMACEUTIQUE :
							// Le laboratoire qui a créé le produit déclare un effet indésirable
							if (p.getLaboratoire().getId().equals(utilisateur.getId())) {
								declaration.setPoids(10.);
							} else {
								declaration.setPoids(0.);
							}
							break;
						default :
							declaration.setPoids(0.);
							break;
					}
				} else {
					switch (utilisateur.getRole()) {
						case PATIENT :
							declaration.setPoids(1.);
							break;
						case CRPV : 
							declaration.setPoids(2.);
							break;
						case CNPV : 
							declaration.setPoids(2.5);
							break;
						case LABORATOIRE_COSMETIQUE :
							// Le laboratoire qui a créé le cosmetique déclare un effet indésirable
							if (p.getLaboratoire().getId().equals(utilisateur.getId())) {
								declaration.setPoids(20.);
							} else {
								declaration.setPoids(0.);
							}
							break;
						default :
							declaration.setPoids(0.);
							break;
					}
				}
				dm.create(declaration);
				response.sendRedirect("accueil.jsp");
			}
			dm.close();
		} else {
			request.getSession().setAttribute("erreur", "Certains champs ne sont pas remplis");
			request.getRequestDispatcher("declaration.jsp").forward(request, response);
		}
	}

}
