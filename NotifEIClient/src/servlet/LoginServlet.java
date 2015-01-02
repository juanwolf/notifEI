package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import manager.*;

/**
 * Servlet implementation class testServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if(!login.isEmpty() && !password.isEmpty()){
			Utilisateur utilisateur = null;
			// On regarde si l'utilisateur est dans la table patient
			PatientManager pm = new PatientManager();
			pm.init();
			utilisateur = pm.retrieveForConnexion(login, password);
			pm.close();
			
			// Si l'utilisateur n'est pas dans la table patient,
			// on regarde s'il est dans la table medecin
			if (utilisateur == null) {
				MedecinManager mm = new MedecinManager();
				mm.init();
				utilisateur = mm.retrieveForConnexion(login, password);
				mm.close();
			}
			
			// Si l'utilisateur n'est pas dans la table patient et medecin,
			// on regarde s'il est dans la table laboratoire
			if (utilisateur == null) {
				LaboratoireManager lm = new LaboratoireManager();
				lm.init();
				utilisateur = lm.retrieveForConnexion(login, password);
				lm.close();
			}
			
			// Si l'utilisateur n'est pas dans la table patient medecin et laboratoire,
			// on regarde s'il est dans la table centre
			if (utilisateur == null) {
				CentreManager cm = new CentreManager();
				cm.init();
				utilisateur = cm.retrieveForConnexion(login, password);
				cm.close();
			}
			
			// Si l'utilisateur a été trouvé dans une des tables,
			// on crée une session contenant les informations liées à cet utilisateur
			if (utilisateur != null) {
				request.getSession().setAttribute("utilisateur", utilisateur);
				request.getRequestDispatcher("accueil.jsp").forward(request, response);
			} else {
				request.getSession().setAttribute("erreur", "L'identifiant ou le mot de passe est invalide");
				request.getRequestDispatcher("connexion.jsp").forward(request, response);
			}
		} else {
			request.getSession().setAttribute("erreur", "Au moins un des champs n'a pas été rempli");
			request.getRequestDispatcher("connexion.jsp").forward(request, response);
		}
	}

}
