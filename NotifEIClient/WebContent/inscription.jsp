<%@ page import="model.Utilisateur" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="inscription.css" />
<link href="css/bootstrap.min.css" rel="stylesheet">
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
		<div  id="champsUtilisateur">
			<div class="form-group">
				<label>Login :</label> 
				<input class="form-control" type="text" name="login"/>
			</div>
			<br/><br/>
			<div class="form-group">
			<label>Mot de passe : </label>
			<input class="form-control" type="password" name="password"/>
			</div>
			<br/><br/>
			<select class="form-control" id="select" name="role">
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
			<div class="form-group" id="divNom">
				<label>Nom :</label> 
				<input class="form-control" type="text" name="nom"/>
				<br/>
			</div>
			<div class="form-group" id="divPrenom">
				<label>Prénom :</label> 
				<input class="form-control" type="text" name="prenom"/>
				<br/>
			</div>
			<div class="form-group" id="divDateNaissance">
				<label>Date de naissance :</label>
				<input class="form-control" type="text" name="dateNaissance"/>
				<br/>
			</div>
			<div class="form-group" id="divTelephone">
				<label>Téléphone :</label>
				<input class="form-control" type="text" name="telephone"/>
				<br/>
			</div>
			<div class="form-group" id="divEmail">
				<label>Email :</label> 
				<input class="form-control" type="text" name="email"/>
				<br/>
			</div>
			<div class="form-group" id="divAdresse">
				<label>Adresse : </label>
				<input class="form-control" type="text" name="adresse"/>
				<br/>
			</div>
			<div class="form-group" id="divVille">
				<label>Ville : </label>
				<input  class="form-control" type="text" name="ville"/>
				<br/>
			</div>
			<div class="form-group" id="divCodePostal">
				<label>Code Postal : </label>
				<input class="form-control" type="text" name="codePostal"/>
				<br/>
			</div>
			<div class="hidden form-group" id="divQualifications">
				<label>Qualifications : </label>
				<input class="form-control" type="text" name="qualifications"/>
				<br/>
			</div>
			<div class="hidden form-group" id="divLieuTravail">
				<label>Lieu de travail : </label>
				<input class="form-control" type="text" name="lieuTravail"/>
				<br/>
			</div>
		</div>
		<div class="form-group">		
			<input class="btn btn-primary" type="submit" value="Envoyer"/>
		</div>
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