<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page import="manager.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accueil</title>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<div id="bulletin-alerte">
	<h1>
	bulletin d'alerte :
	</h1>
<%
	// Pour tous
	boolean alertExist = false;
	DeclarationManager dm = new DeclarationManager();
	dm.init();
	List<EffetIndesirable> eis = dm.retrieveAllDistinctEIs();
	List<ProduitMedical> pm = dm.retrieveAllDistinctPM();
	 for (int i = 0; i < eis.size(); i++) { 
		//out.println(eis.get(i).getNom() + "<br/>");
		for (int j = 0; j < pm.size(); j++) {
			//out.println(pm.get(j).getNom() + "<br/>");
			double poids = dm.retrievePoidsByPMAndEI(eis.get(j).getId(), pm.get(j).getId());
			if (pm.get(i).getClass().equals(ProduitCosmetique.class)) {
				if (poids >= 10.) {
					alertExist = true;
					out.print(String.format("Produit cosmétique : %s | Effet indésirable : %s<br/>", pm.get(j).getNom(), eis.get(j).getNom()));
				}
			} else {
				if (poids >= 20.) {
					alertExist = true;
					out.print(String.format("Produit médical : %s | Effet indésirable : %s<br/>", pm.get(j).getNom(), eis.get(j).getNom()));
				}
			}
		}
	}
	dm.close();
	if (!alertExist) {
		out.print("Aucune alerte n'est recensée");
	}
%>
	</div>
</body>
</html>