<%@page import="model.UserManagerService"%>
<%@page import="model.User"%>
<%@ page import="model.Converter, java.math.*, javax.naming.*"%>

<%!
 	private UserManagerService userManagerService = null;
   	public void jspInit() {
    	try {
       		InitialContext ic = new InitialContext();
       		userManagerService = (UserManagerService) 
       		ic.lookup(UserManagerService.class.getName());
       		System.out.println("Cr�ation du bean");
     	} catch (Exception ex) {
             System.out.println("Cr�ation du bean de conversion impossible :"+ ex.getMessage());
     	}
     	/*try {
            InitialContext ic = new InitialContext();
            userManagerService = (Converter)
            ic.lookup(Converter.class.getName());
            System.out.println("Cr�ation du bean");
          } catch (Exception ex) {
                  System.out.println("Cr�ation du bean de conversion impossible :"+ ex.getMessage());
          }*/
   	}

   	public void jspDestroy() {
       userManagerService = null;
   	}
%>
<html>
<head>
	<title>Inscription</title>
</head>
<body bgcolor="white">
   	<h1>Inscription</h1>
   	<hr/>
	<p>Entrez vos identifiants :</p>
   	<form method="get">
   		<label>Identifiant : </label>
   		<input type="text" name="identifiant"/>
     	<br/>
     	<label>Mot de passe : </label>
     	<input type="password" name="mdp">
     	<br/>
     	<label>Adresse : </label>
   		<input type="text" name="adresse"/>
     	<br/>
     	<input type="submit" value="Inscription"/>
   	</form>

<%
 	String identifiant = request.getParameter("identifiant");
 	if (identifiant != null && identifiant.length() > 0) {
 		String res = "mwa";
 		System.out.println("oki1");
   		userManagerService.init();
   		res = userManagerService.print();
   		//User user = userManagerService.retrieve(1);
   		System.out.println("oki2");
   		//String userName = user.getNom();
   		userManagerService.close();
%>		
 	<p>
   		<%= /*userName*/res %>
 	</p>
<%
		//userManagerService.close();
	}
%>
</body>
</html>