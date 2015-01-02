<%@ page import="model.*" %>
<%
   	if (request.getSession().getAttribute("utilisateur") == null) { 
%>
   		<ul>
   			<li><a href="accueil.jsp">Accueil</a></li>
			<li><a href="inscription.jsp">Inscription</a></li>
			<li><a href="connexion.jsp">Connexion</a></li>
		</ul>
<%
   	} else {
		Utilisateur utilsateur = (Utilisateur) request.getSession().getAttribute("utilisateur"); 
		out.print(String.format("Bienvenue %s", utilsateur.getLogin()));
%>
	<a href="LogoutServlet">Déconnexion</a>
	<a href="recherche.jsp">Recherche</a>
	<a href="declaration.jsp">Déclarer un effet indésirable</a>
<%
	}
%>