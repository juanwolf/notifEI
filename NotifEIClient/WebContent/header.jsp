<%@ page import="model.*" %>
<nav class="navbar navbar-default">
	<div class="container-fluid">
	<!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">NotifEI</a>
    </div>
    <div class="collapse navbar-collapse">
<%
   	if (request.getSession().getAttribute("utilisateur") == null) { 
%>
   		<ul class="nav navbar-nav">
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
</div>