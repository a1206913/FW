<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<%@ page import="logic.Binomial"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.util.ArrayList"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Berechnung</title>
</head>
<body>
	<h1>Das Ergebnis der Berechnung:</h1>


	<table border="1">

		<%
			ArrayList <double[]> arrayList = (ArrayList <double[]>) request.getAttribute("aktienPreis");
			String prob = null;
			for (int j = 0; j < arrayList.size(); j++) {
		%>
		<tr>

			<%
				double[] arr = arrayList.get(j);
					for (int i = 0; i < arr.length; i++) {
						//System.out.print(Binomial.df.format(+arr[i]) + " | ");
						prob = Binomial.df.format(arr[i]);
			%>
			<td width="60" style="background-color:#c1e0ff;"><%=prob%></td>
			<%
				}
			%>

		</tr>

		<%
			}
		%>
	</table><br>
	
	<form action="BinomServlet" method="post">
		<input type="submit" value="Zurück zum Rechner" style="background-color: #408080; color: white" name="back"> 
	</form>
	
	<br><br>
	<h2>Die Call Option</h2>
	
	<table border="1">

		<%
			ArrayList <double[]> call = (ArrayList <double[]>) request.getAttribute("callOption");
			String oneCall = null;
			for (int y = 0; y < Binomial.call.size(); y++) {
		%>
		<tr>

			<%
				double[] arr = call.get(y);
					for (int i = 0; i < arr.length; i++) {
						//System.out.print(Binomial.df.format(+arr[i]) + " | ");
						oneCall = Binomial.df.format(arr[i]);
			%>
			<td width="60" style="background-color:#d7d7d7;"><%=oneCall%></td>
			<%
				}
			%>

		</tr>

		<%
			}
		%>
	</table>



</body>
</html>