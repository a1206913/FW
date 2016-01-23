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
		Time: <input style="margin-left:80px" placeholder="3" type="text" name="T"><br>
		Basiswert: <input style="margin-left:49px" placeholder="100" type="text" name="basisWert"><br>
		Ausübungspreis: <input style="margin-left:9px" placeholder="102" type="text" name="strike"><br>
		Steigfaktor: <input style="margin-left:42px" placeholder="1.10" type="text" name="uFactor"><br>
		Sinkfaktor: <input style="margin-left:47px" placeholder="0.95" type="text" name="sFactor"><br>
		Bruttozins: <input style="margin-left:47px" placeholder="1.01" type="text" name="bZins"><br>
		<br><br>
		<input type="submit" style="background-color: #ffa851; margin-left:155px;" value="Berechne" name="rechner">
	</form>
</body>
</html>