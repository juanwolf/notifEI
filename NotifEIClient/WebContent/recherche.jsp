<%@page import="javax.naming.InitialContext"%>
<%@page import="manager.*"%>
<%@page import="java.util.List"%>
<%@page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%!
	private ProduitMedicalService pms = null;
   	public void jspInit() {
    	try {
       		InitialContext ic = new InitialContext();
       		pms = (ProduitMedicalService) ic.lookup(ProduitMedicalService.class.getName());
     	} catch (Exception ex) {
             System.out.println("Création impossible :"+ ex.getMessage());
     	}
   }

   public void jspDestroy() {
       pms = null;
   }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="inscription.css" />
<title>Recherche</title>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<form action="SearchServlet" method="get">
		<div class="form-group" class="" id="divNom">
			<label>Nom :</label>
			<input class="form-control" type="text" name="nom"/>
			<br/>
		</div>
		<div class="hidden form-group" id="divPrenom">
			<label>Prénom :</label> 
			<input class="form-control" type="text" name="prenom"/>
			<br/>
		</div>
		<div class="hidden form-group" id="divSelect">
			<label>Produit médical :</label>
			<select class="form-control" id="selectProduitsMedicaux" name="produits_medicaux">
<%
	pms.init();
	List<ProduitMedical> list = pms.retrieveAll();
	pms.close();
	for (int i = 0; i < list.size(); i++) {
		out.print(String.format("<option value=\"%s\">%s</option>", list.get(i).getNom(), list.get(i).getNom()));
	}
%>
			</select>
		</div>	
		<select class="form-control" id="select" name="recherche">
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
		<input class="btn btn-primary" type="submit" value="Rechercher"/>
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