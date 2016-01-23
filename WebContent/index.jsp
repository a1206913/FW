<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Binomialverteilung</title>
</head>
<body>
	<h2>Binomialverteilung berechnen</h2>

	<form method="post" action="BinomServlet">
		Time: <input placeholder="3" type="text" name="T"><br>
		Basiswert: <input placeholder="100" type="text" name="basisWert"><br>
		Ausübungspreis: <input placeholder="102" type="text" name="strike"><br>
		Steigfaktor: <input placeholder="1.10" type="text" name="uFactor"><br>
		Sinkfaktor: <input placeholder="0.95" type="text" name="sFactor"><br>
		Bruttozins: <input placeholder="1.01" type="text" name="bZins"><br>
		<br><br>
		<input type="submit" value="Berechne" name="rechner">
	</form>
</body>
</html>