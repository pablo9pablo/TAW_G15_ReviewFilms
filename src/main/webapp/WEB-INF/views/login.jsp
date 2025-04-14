<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h2>Login</h2>

<!-- Mensaje de error -->
<% if (request.getParameter("error") != null) { %>
<p style="color: red;">Usuario o contraseña incorrectos.</p>
<% } %>


<!-- Formulario sin CSRF (para pruebas) -->
<form method="post" action="${pageContext.request.contextPath}/login">
    <label>Usuario: <input type="email" name="username" required></label><br>
    <label>Contraseña: <input type="password" name="password" required></label><br>
    <button type="submit">Entrar</button>
</form>
</body>
</html>