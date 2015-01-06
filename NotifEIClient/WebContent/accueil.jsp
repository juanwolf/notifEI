<%@page import="javax.naming.InitialContext"%>
<%@page import="model.*"%>
<%@page import="java.util.List"%>
<%@page import="manager.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%!
	private DeclarationService ds = null;
   	public void jspInit() {
    	try {
       		InitialContext ic = new InitialContext();
       		ds = (DeclarationService) ic.lookup(DeclarationService.class.getName());
     	} catch (Exception ex) {
             System.out.println("Création impossible :"+ ex.getMessage());
     	}
   }

   public void jspDestroy() {
       ds = null;
   }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Accueil</title>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<div class="container" id="bulletin-alerte">
	<h1>Bulletin d'alerte</h1>
<%
	// Pour tous
	boolean alertExist = false;
	ds.init();
	List<EffetIndesirable> eis = ds.retrieveAllDistinctEIs();
	List<ProduitMedical> pm = ds.retrieveAllDistinctPM();
	 for (int i = 0; i < eis.size(); i++) { 
		for (int j = 0; j < pm.size(); j++) {
			double poids = ds.retrievePoidsByPMAndEI(eis.get(j).getId(), pm.get(j).getId());
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
	ds.close();
	if (!alertExist) {
		out.print("Aucune alerte n'est recensée");
	}
	
%>
	</div>
</body>
</html>