<%@page import="manager.*"%>
<%@page import="java.util.List"%>
<%@page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="inscription.css" />
<title>Recherche</title>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<form action="SearchServlet" method="get">
		<div class="" id="divNom">
			Nom : <input type="text" name="nom"/>
			<br/>
		</div>
		<div class="hidden" id="divPrenom">
			Prénom : 
			<input type="text" name="prenom"/>
			<br/>
		</div>
		<div class="hidden" id="divSelect">
			Produit médical :
			<select id="selectProduitsMedicaux" name="produits_medicaux">
<%
	ProduitMedicalManager pmm = new ProduitMedicalManager();
	pmm.init();
	List<ProduitMedical> list = pmm.retrieveAll();
	pmm.close();
	for (int i = 0; i < list.size(); i++) {
		out.print(String.format("<option value=\"%s\">%s</option>", list.get(i).getNom(), list.get(i).getNom()));
	}
%>
			</select>
		</div>	
		<select id="select" name="recherche">
			<option value="produits_medicaux">Produits Médicaux</option>
			<option value="effets_indesirables">Effets Indésirables</option>
<%
	if (request.getSession().getAttribute("utilisateur") != null) {
		Utilisateur u = (Utilisateur) request.getSession().getAttribute("utilisateur");
		if (u.getClass().equals(Centre.class)) {
			out.print("<option value=\"patients\">Patients</option>");
			out.print("<option value=\"medecins\">Médecins</option>");
			if (u.getRole() == Role.CRPV) {
				out.print("<option value=\"centres\">Centres</option>");
			}
		}
	}
%>			
		</select>
		<br/>
		<input type="submit" value="Rechercher"/>
		<br/>
		<hr/> 
	</form>
<%
	List<Object> l = (List<Object>) request.getSession().getAttribute("recherche");
	if (l != null) {
		for (int i = 0; i < l.size(); i++) {
			out.print(String.format("%s", l.get(i).toString()));	
		}
		request.getSession().setAttribute("recherche", "");
	}
%>

<script type="text/javascript">
	document.getElementById("select").onchange = function() {
		var recherche = this.value;
		switch (recherche) {
		case "patients" :
			document.getElementById("divNom").setAttribute("class", "");
			document.getElementById("divPrenom").setAttribute("class", "");
			document.getElementById("divSelect").setAttribute("class", "hidden");
			break;
		case "medecins" :
			document.getElementById("divNom").setAttribute("class", "");
			document.getElementById("divPrenom").setAttribute("class", "");
			document.getElementById("divSelect").setAttribute("class", "hidden");
			break;
		case "centres" :
			document.getElementById("divNom").setAttribute("class", "");
			document.getElementById("divPrenom").setAttribute("class", "hidden");
			document.getElementById("divSelect").setAttribute("class", "hidden");
			break;
		case "produits_medicaux" :
			document.getElementById("divNom").setAttribute("class", "");
			document.getElementById("divPrenom").setAttribute("class", "hidden");
			document.getElementById("divSelect").setAttribute("class", "hidden");
			break;
		case "effets_indesirables" :
			document.getElementById("divNom").setAttribute("class", "hidden");
			document.getElementById("divPrenom").setAttribute("class", "hidden");
			document.getElementById("divSelect").setAttribute("class", "");
			break;
		default :
			break;
		}
	}
</script>

</body>
</html>