<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
	<form action="LoginServlet" method="post">
		<label>Pseudo :</label><input type="text" name="login"/>
		<label>Password : </label> <input type="password" name="password"/>
		 <input type="submit" value="Connexion"/>
	</form>
<%
	if (request.getSession().getAttribute("erreur") != null) {
		out.print(request.getSession().getAttribute("erreur"));
		request.getSession().invalidate();
	}
%>
</body>
</html>