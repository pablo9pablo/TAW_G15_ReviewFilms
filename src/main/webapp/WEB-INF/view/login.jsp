 <!--OUAIL BOUAZZA MANSOURI : 100%-->

<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<h2>Login</h2>

<% if (request.getParameter("error") != null) { %>
<p>Usuario o contraseña incorrectos.</p>
<% } %>

<form method="post" action="${pageContext.request.contextPath}/login">
    <label>Email:</label>
    <input type="email" name="username" required>

    <label>Contraseña:</label>
    <input type="password" name="password" required>

    <button type="submit">Entrar</button>
</form>

<form action="${pageContext.request.contextPath}/register" method="get">
    <button type="submit">Register</button>
</form>

</body>
</html>
