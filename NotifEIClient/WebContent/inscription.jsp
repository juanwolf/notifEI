<%@ page import="model.Utilisateur" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="inscription.css" />
<title>Inscription</title>
</head>
<body>
<%
	if (request.getSession().getAttribute("utilisateur") != null) {
		response.sendRedirect("accueil.jsp");
	}
%>
	<jsp:include page="/header.jsp" />
	<form action="SubscribeServlet" method="post">
		<div id="champsUtilisateur">
			Login : 
			<input type="text" name="login"/>
			<br/><br/>
			Mot de passe : 
			<input type="password" name="password"/>
			<br/><br/>
			<select id="select" name="role">
				<option value="patient">Patient</option>
				<option value="association-patients">Association de patients</option>
				<option value="medecin-hospitalier">Médecin hospitalier</option>
				<option value="medecin-ville">Médecin de ville</option>
				<option value="laboratoire-pharmaceutique">Laboratoire pharmaceutique</option>
				<option value="laboratoire-cosmetique">Laboratoire cosmétique</option>
			</select>
			<br/><br/>
		</div>
		<div id="autresChamps">
			<div class="" id="divNom">
				Nom : 
				<input type="text" name="nom"/>
				<br/>
			</div>
			<div class="" id="divPrenom">
				Prénom : 
				<input type="text" name="prenom"/>
				<br/>
			</div>
			<div class="" id="divDateNaissance">
				Date de naissance :
				<input type="text" name="dateNaissance"/>
				<br/>
			</div>
			<div class="" id="divTelephone">
				Téléphone :
				<input type="text" name="telephone"/>
				<br/>
			</div>
			<div class="" id="divEmail">
				Email : 
				<input type="text" name="email"/>
				<br/>
			</div>
			<div class="" id="divAdresse">
				Adresse : 
				<input type="text" name="adresse"/>
				<br/>
			</div>
			<div class="" id="divVille">
				Ville : 
				<input type="text" name="ville"/>
				<br/>
			</div>
			<div class="" id="divCodePostal">
				Code Postal : 
				<input type="text" name="codePostal"/>
				<br/>
			</div>
			<div class="hidden" id="divQualifications">
				Qualifications : 
				<input type="text" name="qualifications"/>
				<br/>
			</div>
			<div class="hidden" id="divLieuTravail">
				Lieu de travail : 
				<input type="text" name="lieuTravail"/>
				<br/>
			</div>
		</div>		
		<input type="submit" value="Envoyer"/>
<%   	
		if (request.getSession().getAttribute("erreur") != null) { 
			out.print(String.format("Erreur : %s", request.getSession().getAttribute("erreur")));
			request.getSession().invalidate();
		}
 %>
	</form>
	
	
	<script type="text/javascript">
		document.getElementById("select").onchange = function() {
			var role = this.value;
			switch (role) {
			case "patient" :
				document.getElementById("divPrenom").setAttribute("class", "");
				document.getElementById("divDateNaissance").setAttribute("class", "");
				document.getElementById("divQualifications").setAttribute("class", "hidden");
				document.getElementById("divLieuTravail").setAttribute("class", "hidden");
				break;
			case "association-patients" : 
				document.getElementById("divPrenom").setAttribute("class", "hidden");
				document.getElementById("divDateNaissance").setAttribute("class", "hidden");
				document.getElementById("divQualifications").setAttribute("class", "hidden");
				document.getElementById("divLieuTravail").setAttribute("class", "hidden");
				break;
			case "medecin-hospitalier" : 
				document.getElementById("divPrenom").setAttribute("class", "");
				document.getElementById("divDateNaissance").setAttribute("class", "hidden");
				document.getElementById("divQualifications").setAttribute("class", "");
				document.getElementById("divLieuTravail").setAttribute("class", "");
				break;
			case "medecin-ville" : 
				document.getElementById("divPrenom").setAttribute("class", "");
				document.getElementById("divDateNaissance").setAttribute("class", "hidden");
				document.getElementById("divQualifications").setAttribute("class", "");
				document.getElementById("divLieuTravail").setAttribute("class", "");
				break;
			case "laboratoire-pharmaceutique" : 
				document.getElementById("divPrenom").setAttribute("class", "hidden");
				document.getElementById("divDateNaissance").setAttribute("class", "hidden");
				document.getElementById("divQualifications").setAttribute("class", "hidden");
				document.getElementById("divLieuTravail").setAttribute("class", "hidden");
				break;
			case "laboratoire-cosmetique" : 
				document.getElementById("divPrenom").setAttribute("class", "hidden");
				document.getElementById("divDateNaissance").setAttribute("class", "hidden");
				document.getElementById("divQualifications").setAttribute("class", "hidden");
				document.getElementById("divLieuTravail").setAttribute("class", "hidden");
				break;
			default :
				break;
			}
		};
	</script>
</body>
</html>