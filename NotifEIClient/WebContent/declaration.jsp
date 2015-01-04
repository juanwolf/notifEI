<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.List"%>
<%@page import="manager.*"%>
<%@page import="model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%!
	private ProduitMedicalService pms = null;
	private EffetIndesirableService eis = null;
   	public void jspInit() {
    	try {
       		InitialContext ic = new InitialContext();
       		pms = (ProduitMedicalService) ic.lookup(ProduitMedicalService.class.getName());
       		eis = (EffetIndesirableService) ic.lookup(EffetIndesirableService.class.getName());
     	} catch (Exception ex) {
             System.out.println("Cr�ation impossible :"+ ex.getMessage());
     	}
   }

   public void jspDestroy() {
       pms = null;
       eis = null;
   }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Declaration</title>
</head>
<body> 
	<jsp:include page="/header.jsp" />
<%
	if (request.getSession().getAttribute("utilisateur") == null) {
		response.sendRedirect("accueil.jsp");
	}
%>
	<form action="DeclareServlet" method="post">
		Produit M�dical :
		<select id="select" name="produit_medical">
<%
	pms.init();
	List<ProduitMedical> list = pms.retrieveAll();
	pms.close();
	for (int i = 0; i < list.size(); i++) {
		out.print("<option value=\"" + list.get(i).getNom()
				+ "\">" + list.get(i).getNom() +"</option>");
	}
%>
		</select>
		<br/>
		Effet ind�sirable : 
		<select id="selectEI" name="effet_indesirable">
<%
	eis.init();
	List<EffetIndesirable> listEI = eis.retrieveAll();
	eis.close();
	for (int i = 0; i < listEI.size(); i++) {
		out.print("<option value=\"" + listEI.get(i).getNom()
				+ "\">" + listEI.get(i).getNom() +"</option>");
	}
%>
		</select>
		<br/>
<%
	// uniquement pour les patients pour savoir s'il s'agit d'une d�claration
	// d'un patient quelconque ou d'un patient qui prend ce m�dicament
	if (request.getSession().getAttribute("utilisateur") != null) {
		Utilisateur u = (Utilisateur) request.getSession().getAttribute("utilisateur");
		if (u.getRole() == Role.PATIENT) {
			out.print("Prenez-vous ce m�dicament : <input type=\"checkbox\" name=\"checkbox\" value=\"oui\"/><br/>");		
		}
	}
%>
		<input type="submit" value="D�clarer"/>
	</form>
<%
	if (request.getSession().getAttribute("erreur") != null) {
		out.print(request.getSession().getAttribute("erreur"));
		request.getSession().setAttribute("erreur", "");
	}
%>
</body>
</html>