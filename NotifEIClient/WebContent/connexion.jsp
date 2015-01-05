<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connexion</title>
</head>
<body>
<%
	if (request.getSession().getAttribute("utilisateur") != null) {
		response.sendRedirect("accueil.jsp");
	}
%>
	<jsp:include page="header.jsp" />
	<h1>
		Connexion
	</h1>
	<form class="form-inline" action="LoginServlet" method="post">
	<div class="form-group">
		<label>Pseudo :</label><input class="form-control" type="text" name="login"/>
		</div>
		<div class="form-group">
		<label>Password : </label> <input class="form-control" type="password" name="password"/>
		</div>
		<div class="form-group">
		 <input class="btn btn-primary" type="submit" value="Connexion"/>
		 </div>
	</form>
<%
	if (request.getSession().getAttribute("erreur") != null) {
		out.print(request.getSession().getAttribute("erreur"));
		request.getSession().invalidate();
	}
%>
</body>
</html>