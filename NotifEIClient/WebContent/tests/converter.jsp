<%@ page import="tests.Converter, java.math.*, javax.naming.*"%>

<%!
 private Converter converter = null;
   public void jspInit() {
     try {
       InitialContext ic = new InitialContext();
       converter = (Converter)
       ic.lookup(Converter.class.getName());
     } catch (Exception ex) {
             System.out.println("Création du bean de conversion impossible :"+ ex.getMessage());
     }
   }

   public void jspDestroy() {
       converter = null;
   }
%>
<html>
 <head><title>Convertisseur ref: <%= converter.hashCode() %></title></head>
 <body bgcolor="white">
   <h1>Convertisseur ref: <%= converter.hashCode() %></h1>
   <hr/>
   <p>Entrez un montant à convertir:</p>
   <form method="get">
     <input type="text" name="amount" size="25">
     <br/>
     <p>
     <input type="submit" value="Submit"/>
     <input type="reset" value="Reset"/>
   </form>

<%
 String amount = request.getParameter("amount");
 String hashCode = ""+converter.hashCode();
 if ( amount != null && amount.length() > 0 ) {
   BigDecimal d = new BigDecimal(amount);
   BigDecimal yenAmount = converter.dollarToYen(d);
%>
 <p>
   Convertisseur: <%= hashCode %> indique que <%= amount %> dollars correspondent à <%= yenAmount %> Yens.
 </p>
<%
BigDecimal euroAmount =
converter.yenToEuro(yenAmount);
%>
<%= yenAmount %> Yens correspondent à <%= euroAmount %> Euros.
<%
}
%>
 </body>
</html>