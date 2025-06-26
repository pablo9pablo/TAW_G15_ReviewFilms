
<!--OUAIL BOUAZZA MANSOURI : 100%-->

<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.RegisterDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
    RegisterDTO dto = (RegisterDTO) request.getAttribute("registerDTO");
    if (dto == null) {
        dto = new RegisterDTO(); // valores vacíos por defecto
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/register.css">
</head>
<body>
<h2>Register</h2>

<% if (request.getAttribute("error") != null) { %>
<div class="error"><%= request.getAttribute("error") %></div>
<% } %>

<form method="post" action="/register">
    <label>Email:
        <input type="email" name="email" value="<%= dto.getEmail() != null ? dto.getEmail() : "" %>" required />
    </label>
    <br>
    <label>Contraseña:
        <input type="password" name="password" value="<%= dto.getPassword() != null ? dto.getPassword() : "" %>" required />
    </label>
    <br>
    <button type="submit">Registrar</button>
</form>

</body>
</html>
