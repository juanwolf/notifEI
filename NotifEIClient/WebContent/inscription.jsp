<%@ page import="model.Utilisateur" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inscription</title>
</head>
<body>
	<jsp:include page="/header.jsp" />
	<form action="POST">
		Identifiant : 
		<input type="text" id="id"/>
		<br/><br/>
		Mot de passe : 
		<input type="password" id="password"/>
		<br/><br/>
		<select id="select" name="role">
			<option value="patient">Patient</option>
			<option value="association-patients">Association de patients</option>
			<option value="medecin-hospitalier">Médecin hospitalier</option>
			<option value="medecin-ville">Médecin de ville</option>
			<option value="laboratoire-pharmaceutique">Laboratoire pharmaceutique</option>
			<option value="laboratoire-cosmetique">Laboratoire cosmétique</option>
			<option value="crpv">CRPV</option>
			<option value="cnpv">CNPV</option>
		</select>
		<br/><br/>
		Nom : 
		<input type="text" id="nom"/>
		<br/><br/>
		Prenom : 
		<input type="text" id="prenom"/>
		<br/><br/>
		<input type="date" id="date"/>
		<br/><br/>
		<input type="tel" id="telephone"/>		
		<input type="submit" value="Envoyer"/>
	</form>
	
	
	<script type="text/javascript">
		document.getElementById("select").onchange = function() {
			alert("oki");
		}
	</script>
</body>
</html>